package com.mulatodev.kafka.customers.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mulatodev.kafka.customers.model.Customer;
import com.mulatodev.kafka.customers.service.CustomerService;
/**
 *
 * @author ganaranjo
 */
@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    private final CustomerService customerService;
    
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }
    
    @PostMapping("/customers")
    public Customer save(@RequestBody Customer customer){
        return this.customerService.save(customer);
    }
}
