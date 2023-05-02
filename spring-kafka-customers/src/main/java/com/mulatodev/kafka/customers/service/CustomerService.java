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

    private CustomerEventsService customerEventsService;
    
    public CustomerService(CustomerEventsService customerEventsService){
        this.customerEventsService = customerEventsService;
    }
    
    public Customer save(Customer customer) {
        
        System.out.println("Received " + customer);
        this.customerEventsService.publish(customer);
        return customer;
    }
    
}
