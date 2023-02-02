package com.kafkawiki.kafkawikiconsumer.Kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.util.HashMap;

@Service
public class KafkaConsumer {
    JSONParser parser = new JSONParser();

    public KafkaConsumer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private KafkaTemplate<String, String> kafkaTemplate;
    @KafkaListener(topics = "wikimedia_recentchange", groupId = "serverName")
    public void consume(String message) throws ParseException {

        //convert string messages to json
        JSONObject json = (JSONObject) parser.parse(message);
        String domainChanged = (String) json.get("server_name");

        System.out.println("Message --> "+ message);
        System.out.println("Domain -->"+domainChanged);

        //only send domain part of the event to "domains" topic
        kafkaTemplate.send("domains",domainChanged);
    }
}
