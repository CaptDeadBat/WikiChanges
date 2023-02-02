package com.wordcount.kafkaconsumerwordcount.kafa_consumer_wordcountoutput;

import com.wordcount.kafkaconsumerwordcount.restapi_domainchanges.DomainChange;
import com.wordcount.kafkaconsumerwordcount.restapi_domainchanges.DomainChangeDAO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaWordCountConsumer {
    @Autowired
    private DomainChangeDAO domainChangeDao;



    String domainChanged, timesChangedString;
    int timesChangedInt;
        @KafkaListener(topics = "wordcount-output",properties = {"enable.auto.commit:false", "auto.offset.reset:earliest"})
    public void consume(ConsumerRecord<String, String> record) {
            domainChanged=record.key();
            timesChangedString=record.value();
            timesChangedInt=Integer.parseInt(timesChangedString);

            System.out.println("New data --->"+domainChanged+timesChangedString);

            if(domainChanged.length()<5){
                DomainChange domainChange = new DomainChange(timesChangedInt,domainChanged);
                domainChangeDao
                        .addDomain(domainChange);
            }



    }
}
