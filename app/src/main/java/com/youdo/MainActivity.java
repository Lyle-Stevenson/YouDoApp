package com.youdo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;

import com.youdo.Calendar.CalendarActivity;
import com.youdo.ImportantDates.ImpGoalsActivity;
import com.youdo.Schedule.ScheduleActivity;
import com.youdo.TodoList.TodoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Sets display to that of the activity_main layout
        setContentView(R.layout.activity_main);

        //Initialises button variables.
        Button buttonCalendar = findViewById(R.id.buttonCalendar);
        Button buttonTodo = findViewById(R.id.buttonTodo);
        Button buttonDates = findViewById(R.id.buttonDates);
        Button buttonSched = findViewById(R.id.buttonSchedule);

        Button buttonHome = findViewById(R.id.buttonHome);
        Button footerCalendar = findViewById(R.id.footerCalendar);
        Button footerTodo = findViewById(R.id.footerTodo);
        Button footerImp = findViewById(R.id.footerImp);
        Button footerSched = findViewById(R.id.footerSched);

        //Footer buttons intents
        footerCalendar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creates and intent to change activity from main to calendar
                Intent calender = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(calender);
            }
        });
        footerTodo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creates and intent to change activity from main to to do
                Intent todo = new Intent(MainActivity.this, TodoActivity.class);
                startActivity(todo);
            }
        });
        footerImp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creates and intent to change activity from main to important dates
                Intent imp = new Intent(MainActivity.this, ImpGoalsActivity.class);
                startActivity(imp);
            }
        });
        footerSched.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creates and intent to change activity from main to scheduler
                Intent sched = new Intent(MainActivity.this, ScheduleActivity.class);
                startActivity(sched);
            }
        });

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

        //Function to listen for importantdates button being clicked
        buttonDates.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creates and intent to change activity from main to importantdates
                Intent dates = new Intent(MainActivity.this, ImpGoalsActivity.class);
                startActivity(dates);
            }
        });

        //Function to listen for scheduler button being clicked
        buttonSched.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creates and intent to change activity from main to scheduler
                Intent schedule = new Intent(MainActivity.this, ScheduleActivity.class);
                startActivity(schedule);
            }
        });
    }
}
