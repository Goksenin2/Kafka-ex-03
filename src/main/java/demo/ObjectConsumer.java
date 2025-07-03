package demo;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ObjectConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,  "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG,           "student-consumer");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,  "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,   StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StudentDeserializer.class.getName());

        try (KafkaConsumer<String, Student> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Collections.singletonList("student-topic"));
            while (true) {
                ConsumerRecords<String, Student> records = consumer.poll(Duration.ofMillis(200));
                for (var record : records) {
                    System.out.println("Received: " + record.value());
                }
                if (!records.isEmpty()) break;
            }
        }
    }
}
