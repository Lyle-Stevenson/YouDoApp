package com.youdo;

/**
 * Created by Abigail on 15/02/2018.
 */

public class Event {
    private String name;
    private String description;

    public Event (){}

    public Event(String name,String description){
        this.setName(name);
        this.setDescription(description);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
