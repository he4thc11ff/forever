package scau.lzl.rest.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaConfig {

    @Bean("kafkaTemplate")
    public KafkaProducer<String, String> getKafkaProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "ali2c8g:9092");
        props.put("acks", "all");
        props.put("retries", "5");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("compression.type", "lz4");
        props.put("linger.ms", "5");
        props.put("enable.idempotence", "true");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        return producer;
    }

}
