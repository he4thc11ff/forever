package scau.lzl.rest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.lzl.rest.entity.Tracker;

@Service
public class TrackService {

    private static ObjectMapper mapper = new ObjectMapper();

    @Autowired
    KafkaProducer<String, String> kafkaTemplate;

    public void sendToKafka(String name, String referer, int position) {
        Tracker tracker = new Tracker(name, System.currentTimeMillis(), referer, position);
        ProducerRecord<String, String> record = null;
        try {
            record = new ProducerRecord<>("track", name, mapper.writeValueAsString(tracker));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        kafkaTemplate.send(record);
    }
}
