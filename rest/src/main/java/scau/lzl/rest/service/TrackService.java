package scau.lzl.rest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kudu.client.KuduClient;
import org.apache.kudu.client.KuduException;
import org.apache.kudu.client.KuduTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.lzl.rest.entity.Tracker;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class TrackService {

    private static final AtomicLong id = new AtomicLong(1);
    private static ObjectMapper mapper = new ObjectMapper();

    @Autowired
    KafkaProducer<String, String> kafkaTemplate;

    public void sendToKafka(long uid, String referer, int position) {
        Tracker tracker = new Tracker(id.getAndIncrement(), uid, System.currentTimeMillis(), referer, position);
        ProducerRecord<String, String> record = null;
        try {
            record = new ProducerRecord<>("track", uid + "", mapper.writeValueAsString(tracker));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        kafkaTemplate.send(record);
    }

//    private static long getInitialValue() throws KuduException {
//        KuduClient client = new KuduClient.KuduClientBuilder("ali2c8g:7051").build();
//        if (client.tableExists("track")) {
//            KuduTable table = client.openTable("track");
//            table.ge
//        } else {
//            return 0;
//        }
//    }
}
