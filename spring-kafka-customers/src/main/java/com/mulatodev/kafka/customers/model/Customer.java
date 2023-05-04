package com.mulatodev.kafka.customers.model;

import lombok.Data;

/**
 *
 * @author ganaranjo
 */
@Data
public class Customer {

    public Customer(){
        
    }
    
    private Long id;
    private String name;
    private String email;

}
