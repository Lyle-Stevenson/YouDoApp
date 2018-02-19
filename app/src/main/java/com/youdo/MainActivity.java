package com.youdo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Sets display to that of the activity_main layout
        setContentView(R.layout.activity_main);

        //Initialises button variables.
        Button buttonCalendar = findViewById(R.id.buttonCalendar);
        Button buttonTodo = findViewById(R.id.buttonTodo);

        //Function to listen for calendar button being clicked
        buttonCalendar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creates and intent to change activity from main to calendar
                Intent calender = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(calender);
            }
        });

        //Function to listen for to do button being clicked
        buttonTodo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creates and intent to change activity from main to to do
                Intent todo = new Intent(MainActivity.this, TodoActivity.class);
                startActivity(todo);
            }
        });

    }

    public void buttonSchedule(View view) {
        Intent schedule = new Intent(MainActivity.this, ScheduleActivity.class);
        startActivity(schedule);
    }
}
