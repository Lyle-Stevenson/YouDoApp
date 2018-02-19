package com.youdo;

/**
 * Created by Lyle on 19/02/2018.
 */

//Schedule item object
public class ScheduleItem {
    //Variables a schdule item object should have
    private String name;
    private String startTime;
    private String endTime;

    //Empty constructor
    public ScheduleItem (){}

    //Constructor taking in ScheduleItem details
    public ScheduleItem(String name, String start, String end){
        this.setName(name);
        this.setStart(start);
        this.setEnd(end);
    }

    //Setters and getters for variables
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getStart() { return startTime; }

    public void setStart(String start) { this.startTime = start; }

    public String getEnd() { return endTime; }

    public void setEnd(String end) {
        this.endTime = end;
    }
}
