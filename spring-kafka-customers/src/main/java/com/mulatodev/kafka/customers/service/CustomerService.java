package com.mulatodev.kafka.customers.service;

import org.springframework.stereotype.Service;

import com.mulatodev.kafka.customers.model.Customer;
import com.mulatodev.kafka.customers.service.CustomerEventsService;

/**
 *
 * @author ganaranjo
 */
@Service
public class CustomerService {

    private final CustomerEventsService customerEventsService;
    
    public CustomerService(CustomerEventsService customerEventsService){

        super();
        this.customerEventsService = customerEventsService;
    }
    
    public Customer save(Customer customer) {
        
        System.out.println("Received " + customer.getName());
        this.customerEventsService.publish(customer);
        
        return customer;
    }
    
}
