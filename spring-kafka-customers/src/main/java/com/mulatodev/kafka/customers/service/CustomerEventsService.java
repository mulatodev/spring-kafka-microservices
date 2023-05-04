package com.mulatodev.kafka.customers.service;

import com.mulatodev.kafka.customers.model.Customer;
import com.mulatodev.kafka.customers.events.CustomerCreatedEvent;
import com.mulatodev.kafka.customers.events.EventType;
import com.mulatodev.kafka.customers.events.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;
/**
 *
 * @author ganaranjo
 */
@Component
public class CustomerEventsService {

    @Autowired
    private KafkaTemplate<String, Event<?>> producer;

    public CustomerEventsService(){
        
    }
    
    public void publish(Customer customer) {
        
        CustomerCreatedEvent customerCreatedEvent = new CustomerCreatedEvent();
        customerCreatedEvent.setData(customer);
        customerCreatedEvent.setId(UUID.randomUUID().toString());
        customerCreatedEvent.setType(EventType.CREATED);
        customerCreatedEvent.setDate(new Date());

        producer.send("fpvdight-customer", customerCreatedEvent);
    }    
}
