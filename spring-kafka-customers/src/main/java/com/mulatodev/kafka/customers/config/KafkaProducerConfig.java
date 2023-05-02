package com.mulatodev.kafka.customers.config;

import org.springframework.kafka.core.ProducerFactory;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;

import com.mulatodev.kafka.customers.events.Event;

/**
 *
 * @author ganaranjo
 */
@Configuration
public class KafkaProducerConfig {
	
    private final String bootstrapAddress = "moped.srvs.cloudkafka.com:9094";
    //private final String jassCfg = String.format();
	
    @Bean
    public ProducerFactory<String, Event<?>> producerFactory() {
        
        Map<String, Object> configProps = new HashMap<>();
        
        configProps.put (
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress
        );
        
        configProps.put (
                ProducerConfig.SECURITY_PROVIDERS_CONFIG,
                  "org.apache.kafka.common.security.scram.ScramLoginModule "
                + "required username=fpvdight "
                + "password=8neVHD9Fdah6D3NpwnHepIyXnzXjJK7U;"
        );
        
        configProps.put (
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class
        );
        
        configProps.put (
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class
        );
        
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, Event<?>> kafkaTemplate() {
        
        return new KafkaTemplate<>(producerFactory());
    }
    
}
