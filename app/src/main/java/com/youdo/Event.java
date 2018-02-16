package com.youdo;

/**
 * Created by Abigail on 15/02/2018.
 */

public class Event {
    private String name;
    private String description;
    private String date;
    private String time;

    public Event (){}

    public Event(String name,String description, String date, String time){
        this.setName(name);
        this.setDescription(description);
        this.setDate(date);
        this.setTime(time);
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

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }

    public void setTime(String time) {
        this.time = time;
    }


}
