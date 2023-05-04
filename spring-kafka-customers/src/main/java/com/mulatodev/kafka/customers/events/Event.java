package com.mulatodev.kafka.customers.events;

import lombok.Data;

import java.util.Date;

/**
 *
 * @author ganaranjo
 */
@Data
public abstract class Event <T> {
    
    public Event(){
    
    }
    
    private String id;
    private Date date;
    private EventType type;
    private T data;
    
}
