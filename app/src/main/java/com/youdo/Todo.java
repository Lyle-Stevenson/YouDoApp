package com.youdo;

/**
 * Created by Lyle on 18/01/2018.
 * Class for a to do list item
 */

public class Todo {

    private String name;

    public Todo (){}

    public Todo(String name){
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
