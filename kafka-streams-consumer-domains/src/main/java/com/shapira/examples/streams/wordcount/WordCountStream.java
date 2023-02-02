package com.shapira.examples.streams.wordcount;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.StreamsBuilder;
import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Pattern;

public class WordCountStream {

    public static void main(String[] args) throws Exception{

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9093");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");



        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, String> source = builder.stream("domains"); //input topic of kstream


        final Pattern pattern = Pattern.compile("\\W+");
        KStream counts  = source.flatMapValues(value-> Arrays.asList(pattern.split(value.toLowerCase())))
                .map((key, value) -> new KeyValue<Object, Object>(value, value))
                .filter((key, value) -> (!value.equals("org")))
                .filter((key, value) -> (!value.equals("www")))
                .groupByKey()
                .count().mapValues(value->Long.toString(value)).toStream();

        //filter out "org" and "www" and group each message with domain as key and count as value

        counts.to("wordcount-output");   //output topic of the kstream

        KafkaStreams streams = new KafkaStreams(builder.build(), props);


        streams.cleanUp();

        streams.start();

//        Thread.sleep(5000L);
//                                          Use to run it for a finite time
//        streams.close();



        //log4j.rootLogger=TRACE, stderr

    }
}
