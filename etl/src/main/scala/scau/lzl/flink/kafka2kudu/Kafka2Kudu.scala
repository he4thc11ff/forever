package scau.lzl.flink.kafka2kudu

import java.util.Properties

import org.apache.flink.configuration.Configuration
import org.apache.flink.runtime.state.{FunctionInitializationContext, FunctionSnapshotContext}
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode
import org.apache.flink.streaming.api.checkpoint.CheckpointedFunction
import org.apache.flink.streaming.api.environment.CheckpointConfig
import org.apache.flink.streaming.api.functions.sink.{RichSinkFunction, SinkFunction}
import org.apache.flink.streaming.api.{CheckpointingMode, TimeCharacteristic}
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.kudu.client._
import org.slf4j.LoggerFactory
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

    source.addSink(new KuduSink())

    env.execute()
  }

  // impala 建表语句
  // create table track(id BIGINT,uid BIGINT,`timestamp` BIGINT, referer STRING, `position` INT, PRIMARY KEY (id,uid,`timestamp`)) STORED AS KUDU;
  class KuduSink extends RichSinkFunction[ObjectNode] with CheckpointedFunction {
    val logger = LoggerFactory.getLogger(this.getClass)

    val KUDU_HOST = "ali2c8g:7051"
    val TABLE_NAME = "impala::heathcliff.track" // 该表由impala间接创建，具体表名要到kudu master 的 web ui 查

    var client: KuduClient = _
    var session: KuduSession = _
    var table: KuduTable = _

    var insertedCount = 0
    val batchCount = 3

    override def open(parameters: Configuration): Unit = {
      client = new KuduClient.KuduClientBuilder(KUDU_HOST).build;
      session = client.newSession()
      session.setFlushMode(SessionConfiguration.FlushMode.MANUAL_FLUSH)

      if (client.tableExists(TABLE_NAME)) {
        table = client.openTable(TABLE_NAME)
      } else {
        // 注意，抛出的异常没有try catch的话，将直接调用close方法，若想获取异常信息，必须要try catch（close方法如果能传入一个异常对象就好了。。）
        throw new Exception(s"表 ${TABLE_NAME} 不存在")
      }
    }

    override def invoke(value: ObjectNode, context: SinkFunction.Context[_]): Unit = {
      val insert = table.newInsert()
      val row = insert.getRow

      row.addLong("id", value.get("value").get("id").asLong())
      row.addLong("uid", value.get("value").get("uid").asLong())
      row.addLong("timestamp", value.get("value").get("timestamp").asLong())
      row.addString("referer", value.get("value").get("referer").asText())
      row.addInt("position", value.get("value").get("position").asInt())

      session.apply(insert)

      insertedCount = insertedCount + 1
      if (insertedCount >= batchCount) {
        logger.info("batch inserted")

        session.flush()
        insertedCount = 0
      }
    }

    override def close(): Unit = {
      session.flush()

      if (session != null) {
        session.close()
      }

      if (client != null) {
        client.close()
      }
    }

    override def snapshotState(functionSnapshotContext: FunctionSnapshotContext): Unit = {
      // 确保checkpoint完成之前，flush到kudu
      session.flush()
    }

    override def initializeState(functionInitializationContext: FunctionInitializationContext): Unit = {}
  }

  def getKafkaPros(): Properties = {
    val props = new Properties()
    props.setProperty("bootstrap.servers", "ali1c2g:9092")
    props.setProperty("group.id", "flink")
    props
  }
}
