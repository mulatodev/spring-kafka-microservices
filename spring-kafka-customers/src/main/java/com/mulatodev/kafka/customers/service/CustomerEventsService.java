package com.mulatodev.kafka.customers.service;

import com.mulatodev.kafka.customers.model.Customer;
import com.mulatodev.kafka.customers.events.CustomerCreatedEvent;
import com.mulatodev.kafka.customers.events.EventType;
import com.mulatodev.kafka.customers.events.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${topic.customer.name:customers}")
    private String topicCustomer;

    public CustomerEventsService(){
        
    }
    
    public void publish(Customer customer) {
        
        CustomerCreatedEvent created = new CustomerCreatedEvent();
        created.setData(customer);
        created.setId(UUID.randomUUID().toString());
        created.setType(EventType.CREATED);
        created.setDate(new Date());

        this.producer.send(topicCustomer, created);
    }    
}
