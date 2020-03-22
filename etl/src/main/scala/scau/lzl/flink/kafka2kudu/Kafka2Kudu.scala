package scau.lzl.flink.kafka2kudu

import java.util
import java.util.Properties

import org.apache.flink.configuration.Configuration
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode
import org.apache.flink.streaming.api.environment.CheckpointConfig
import org.apache.flink.streaming.api.functions.sink.{RichSinkFunction, SinkFunction}
import org.apache.flink.streaming.api.{CheckpointingMode, TimeCharacteristic}
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.kudu.{ColumnSchema, Schema, Type}
import org.apache.kudu.client.{CreateTableOptions, KuduClient, KuduTable}
import scau.lzl.flink.serialization.MyJSONKeyValueDeserializationSchema

object Kafka2Kudu {
  def main(args: Array[String]): Unit = {
    import org.apache.flink.api.scala._
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(2)
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    env.enableCheckpointing(10000, CheckpointingMode.EXACTLY_ONCE)
    env.getCheckpointConfig.setMinPauseBetweenCheckpoints(10000)
    env.getCheckpointConfig.setCheckpointTimeout(60000)
    env.getCheckpointConfig.setMaxConcurrentCheckpoints(1)
    env.getCheckpointConfig.enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION)
    env.getCheckpointConfig.setPreferCheckpointForRecovery(true)

    val consumer = new FlinkKafkaConsumer[ObjectNode]("track", new MyJSONKeyValueDeserializationSchema(true), getKafkaPros())
    consumer.setCommitOffsetsOnCheckpoints(true)
    consumer.setStartFromEarliest()

    val source = env.addSource(consumer)

    source.addSink(objectNode => println(objectNode.get("metadata")))

    env.execute()
  }

  class KuduSink extends RichSinkFunction[Tracker] {
    val KUDU_HOST = "ali2c8g:7051"
    val TABLE_NAME = "track"

    var table: KuduTable = _

    override def open(parameters: Configuration): Unit = {
      val client = new KuduClient.KuduClientBuilder(KUDU_HOST).build;

      if (client.tableExists(TABLE_NAME)) {
        table = client.openTable(TABLE_NAME)
      } else {
        createTable(table)
      }
    }

    override def invoke(value: Tracker, context: SinkFunction.Context[_]): Unit = {

    }

    override def close(): Unit = super.close()

    def createTable(table: KuduTable): Unit = {
//      case class Tracker(id: Long, uid: Long, timestamp: Long, referer: String, position: Int, entry: String, exit: String)
      val columnSchemas = new util.ArrayList[ColumnSchema]()
      columnSchemas.add(new ColumnSchema.ColumnSchemaBuilder("id", Type.INT64).key(true).build())
      columnSchemas.add(new ColumnSchema.ColumnSchemaBuilder("uid", Type.INT64).key(true).build())
      columnSchemas.add(new ColumnSchema.ColumnSchemaBuilder("timestamp", Type.INT64).nullable(false).build())
      columnSchemas.add(new ColumnSchema.ColumnSchemaBuilder("referer", Type.STRING).nullable(false).build())
      columnSchemas.add(new ColumnSchema.ColumnSchemaBuilder("position", Type.INT32).nullable(false).build())
      columnSchemas.add(new ColumnSchema.ColumnSchemaBuilder("entry", Type.STRING).nullable(false).build())
      columnSchemas.add(new ColumnSchema.ColumnSchemaBuilder("exit", Type.STRING).nullable(false).build())
      val schema = new Schema(columnSchemas)

      val createTableOptions = new CreateTableOptions
      val columns = new util.ArrayList[String]()
      columns.add("id")
      createTableOptions.addHashPartitions(columns, 8)
    }
  }

  def getKafkaPros(): Properties = {
    val props = new Properties()
    props.setProperty("bootstrap.servers", "ali2c8g:9092")
    props.setProperty("group.id", "flink")
  }
}
