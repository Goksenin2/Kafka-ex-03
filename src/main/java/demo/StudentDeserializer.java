package demo;

import org.apache.kafka.common.serialization.Deserializer;
import java.io.*;
import java.util.Map;

public class StudentDeserializer implements Deserializer<Student> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) { }

    @Override
    public Student deserialize(String topic, byte[] data) {
        if (data == null) return null;
        try (ObjectInputStream ois =
                 new ObjectInputStream(new ByteArrayInputStream(data))) {
            return (Student) ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing Student", e);
        }
    }

    @Override
    public void close() { }
}
