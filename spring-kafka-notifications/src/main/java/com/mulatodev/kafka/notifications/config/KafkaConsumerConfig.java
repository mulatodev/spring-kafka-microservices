package com.mulatodev.kafka.notifications.config;

import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.ConsumerFactory;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

import java.util.HashMap;
import java.util.Map;

import com.mulatodev.kafka.notifications.events.Event;
/**
 *
 * @author ganaranjo
 */
@EnableKafka
@Configuration
public class KafkaConsumerConfig {
	
	private final String bootstrapAddress = "moped.srvs.cloudkafka.com:9094";

    @Bean
    public ConsumerFactory<String, Event<?>> consumerFactory() {
        Map<String, String> props = new HashMap<>();
        
        props.put (
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress
        );
        
        props.put (
                JsonSerializer.TYPE_MAPPINGS,
                "com.mulatodev:com.mulatodev.kafka.notifications.events.Event"
        );

        final JsonDeserializer<Event<?>> jsonDeserializer = 
                new JsonDeserializer<>();
        
        return new DefaultKafkaConsumerFactory (
                props,
                new StringDeserializer(),
                jsonDeserializer
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Event<?>>
    kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, Event<?>> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
