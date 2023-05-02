package com.mulatodev.kafka.notifications.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

import com.mulatodev.kafka.notifications.events.Event;
import com.mulatodev.kafka.notifications.events.CustomerCreatedEvent;
/**
 *
 * @author ganaranjo
 */
@Slf4j
@Component
public class CustomerEventsService {

    public CustomerEventsService(){
        
    }
    
    @KafkaListener (
            topics = "${topic.customer.name:customers}",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "grupo1"
    )
    
    public void consumer(Event<?> event) {
       
        if (event.getClass().isAssignableFrom(CustomerCreatedEvent.class)) {
            
            CustomerCreatedEvent customerCreatedEvent = 
                    (CustomerCreatedEvent) event;
            
            log.info ("Received Customer created event .... with Id={}, data={}",
                    customerCreatedEvent.getId(),
                    customerCreatedEvent.getData().toString()
            );
        }
    }

}
