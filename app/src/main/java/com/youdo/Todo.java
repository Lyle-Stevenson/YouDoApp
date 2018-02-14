package com.youdo;

/**
 * Created by Lyle on 18/01/2018.
 * Class for a to do list item
 */

public class Todo {

    private String name;
    private String date;
    private String cat;
    public Todo (){}

    public Todo(String name,String category, String date){
        this.setName(name);
        this.setCat(category);
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


public void setCat(String category) {this.cat = category; }

public String getCat() {return cat;}
}
