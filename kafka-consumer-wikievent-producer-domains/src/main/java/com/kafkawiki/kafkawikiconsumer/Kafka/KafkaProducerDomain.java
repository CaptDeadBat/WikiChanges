package com.kafkawiki.kafkawikiconsumer.Kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerDomain {
    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerDomain(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


}
