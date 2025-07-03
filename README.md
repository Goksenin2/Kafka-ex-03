# kafka-ex-03

This repository contains two simple Java applications using Apache Kafka:
one produces serialized Student objects into a Kafka topic, and
the other consumes and prints them.

---

## Prerequisites

- Java 11 or later installed  
- Maven installed or Docker available (for Maven image)  
- Kafka and ZooKeeper running on localhost:9092 (via Docker or local install)  

---

## Build

Run in the project root (where `pom.xml` is):
mvn clean package

This compiles your code and downloads Kafka client dependencies.

---

## Run

### Create topic

In a separate terminal (assuming Docker container name `kafka`):

### Produce messages

mvn exec:java \ -Dexec.mainClass="demo.ObjectProducer"

You should see logs like:

Producing: Student{id=1, name='Student-1'} Producing: Student{id=2, name='Student-2'} Producing: Student{id=3, name='Student-3'}

### Consume messages

In another terminal:

mvn exec:java \ -Dexec.mainClass="demo.ObjectConsumer"

Output:
Received: Student{id=1, name='Student-1'} Received: Student{id=2, name='Student-2'} Received: Student{id=3, name='Student-3'}
