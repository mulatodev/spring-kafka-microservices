package com.mulatodev.kafka.customers.events;

import com.mulatodev.kafka.customers.model.Customer;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author ganaranjo
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerCreatedEvent extends Event<Customer> {
    
}
