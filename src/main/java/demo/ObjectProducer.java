package demo;

import org.apache.kafka.clients.producer.*;
import java.util.Properties;

public class ObjectProducer {
    public static final String TOPIC = "student-topic";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer",   "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "demo.StudentSerializer");

        try (KafkaProducer<String, Student> producer = new KafkaProducer<>(props)) {
            for (int i = 1; i <= 3; i++) {
                Student s = new Student(i, "Student-" + i);
                System.out.println("Producing: " + s);
                producer.send(new ProducerRecord<>(TOPIC, "key" + i, s));
            }
        }
    }
}
