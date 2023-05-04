package com.mulatodev.kafka.customers.config;

import org.springframework.kafka.core.ProducerFactory;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.mulatodev.kafka.customers.events.Event;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.security.plain.PlainLoginModule;

/**
 *
 * @author ganaranjo
 */
@Configuration
public class KafkaProducerConfig {
	
    @Bean
    public ProducerFactory<String, Event<?>> producerFactory() {
        
        Map<String, Object> configProps = new HashMap<>();

        String jaasTemplate =
                "org.apache.kafka.common.security.scram.ScramLoginModule "
                        + "required username=\"%s\" password=\"%s\";";

        String username = "fpvdight";
        String password = "8neVHD9Fdah6D3NpwnHepIyXnzXjJK7U";

        String jaasCfg = String.format(
                jaasTemplate,
                username,
                password
        );

        configProps.put("bootstrap.servers", "moped.srvs.cloudkafka.com:9094");
        configProps.put("group.id", "fpvdight-customer");
        configProps.put("enable.auto.commit", "true");
        configProps.put("auto.commit.interval.ms", "1000");
        configProps.put("auto.offset.reset", "earliest");
        configProps.put("session.timeout.ms", "30000");
        //configProps.put("key.deserializer", StringDeserializer.class.getName());
        //configProps.put("value.deserializer", StringDeserializer.class.getName());
        configProps.put("key.serializer", StringSerializer.class.getName());
        configProps.put("value.serializer", JsonSerializer.class.getName());
        configProps.put("security.protocol", "SASL_SSL");
        configProps.put("sasl.mechanism", "SCRAM-SHA-256");
        configProps.put("sasl.jaas.config", jaasCfg);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, Event<?>> kafkaTemplate() {
        
        return new KafkaTemplate<>(producerFactory());
    }
    
}
