package com.youdo.Calendar;

/**
 * Event object
 */

public class Event {
    private String name;
    private String date;
    private String time;

    public Event (){}

    public Event(String name, String date, String time){
        this.setName(name);
        this.setDate(date);
        this.setTime(time);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }

    public void setTime(String time) {
        this.time = time;
    }


}
