package com.youdo.ImportantDates;

/**
 * Created by Lyle on 23/03/2018.
 */

public class Date {
    private String name;
    private String date;

    public Date(){}

    public Date(String name, String date){
        this.setName(name);
        this.setDate(date);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
