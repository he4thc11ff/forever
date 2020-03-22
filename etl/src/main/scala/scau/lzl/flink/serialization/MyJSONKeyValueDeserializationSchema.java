package scau.lzl.flink.serialization;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.apache.flink.api.java.typeutils.TypeExtractor.getForClass;

public class MyJSONKeyValueDeserializationSchema implements KafkaDeserializationSchema<ObjectNode> {
    private static final long serialVersionUID = 1509391548173891955L;

    private final boolean includeMetadata;
    private ObjectMapper mapper;

    public MyJSONKeyValueDeserializationSchema(boolean includeMetadata) {
        this.includeMetadata = includeMetadata;
    }

    @Override
    public ObjectNode deserialize(ConsumerRecord<byte[], byte[]> record) throws Exception {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        ObjectNode node = mapper.createObjectNode();

        try {
            if (mapper == null) {
                mapper = new ObjectMapper();
            }
            node = mapper.createObjectNode();
//            if (record.key() != null) { key 为普通String 不是JSON对象，所以序列化错误
//                System.out.println("---");
//                System.out.println(new String(record.key(), StandardCharsets.UTF_8));
//                System.out.println("---");
//                node.set("key", mapper.readValue(record.key(), JsonNode.class));
//            }
            if (record.value() != null) {
                node.set("value", mapper.readValue(record.value(), JsonNode.class));
            }
            if (includeMetadata) {
                node.putObject("metadata")
                        .put("offset", record.offset())
                        .put("topic", record.topic())
                        .put("partition", record.partition());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return node;
    }

    @Override
    public boolean isEndOfStream(ObjectNode nextElement) {
        return false;
    }

    @Override
    public TypeInformation<ObjectNode> getProducedType() {
        return getForClass(ObjectNode.class);
    }
}
