package com.mulatodev.kafka.notifications.events;

import java.util.Date;

/**
 *
 * @author ganaranjo
 */
public abstract class Event <T> {
    private String id;
    private Date date;
    private EventType type;
    private T data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
    
}
