package io.conduktor.demos;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemo {
    private static final Logger log = LoggerFactory.getLogger(ProducerDemo.class.getSimpleName());
    public static void main(String[] args) {
        log.info("Hello World!");

        //create producer properties
        Properties properties = new Properties();


        //connect to local
      //  properties.setProperty("bootstrap.servers","127.0.0.1.9092");

        //connect to upstash server
        properties.setProperty("bootstrap.servers", "adapting-iguana-8374-us1-kafka.upstash.io:9092");
        properties.setProperty("sasl.mechanism", "SCRAM-SHA-256");
        properties.setProperty("security.protocol", "SASL_SSL");
        properties.setProperty("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"YWRhcHRpbmctaWd1YW5hLTgzNzQk8yIesy3TSmA0Xyg1LJ7K2aq5NJJ_qjXfGcQ\" password=\"YTYxYWVlNGQtNmE1Ni00NWNmLWIxNTQtZjM2NTM1MTkyMzQw\";");
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        //set producer properties
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer",StringSerializer.class.getName());

        //create producer
        KafkaProducer<String,String> producer = new KafkaProducer<>(properties);

        //create producer record
        ProducerRecord<String,String> producerRecord = new ProducerRecord<>("demo_java","hello world");

        //send data
        producer.send(producerRecord);

        //flush and close producer
        producer.flush();
        producer.close();
    }
}
