package demo;

import org.apache.kafka.common.serialization.Serializer;
import java.io.*;
import java.util.Map;

public class StudentSerializer implements Serializer<Student> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) { }

    @Override
    public byte[] serialize(String topic, Student s) {
        if (s == null) return null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos  = new ObjectOutputStream(baos)) {
            oos.writeObject(s);
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error serializing Student", e);
        }
    }

    @Override
    public void close() { }
}
