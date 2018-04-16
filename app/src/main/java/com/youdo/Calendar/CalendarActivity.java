package com.youdo.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;

import com.youdo.DatabaseHelper;
import com.youdo.ImportantDates.ImpGoalsActivity;
import com.youdo.MainActivity;
import com.youdo.R;
import com.youdo.Schedule.ScheduleActivity;
import com.youdo.TodoList.AddTaskActivity;
import com.youdo.TodoList.TodoActivity;

import java.util.ArrayList;

public class
CalendarActivity extends AppCompatActivity {

    private DatabaseHelper database;
    ListView eventList;
    ArrayAdapter<Event> mAdapter;
    private CalendarView calendarView; //Reference to the calendar view.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        database = new DatabaseHelper(this);//Database reference

        eventList = (ListView) findViewById(R.id.eventView); //Listview reference

        calendarView = (CalendarView) findViewById(R.id.calendarView); //Calendarview reference

        Button buttonHome = findViewById(R.id.buttonHome);
        Button footerCalendar = findViewById(R.id.footerCalendar);
        Button footerTodo = findViewById(R.id.footerTodo);
        Button footerImp = findViewById(R.id.footerImp);
        Button footerSched = findViewById(R.id.footerSched);

        //Header and footer intents.
        buttonHome.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent home = new Intent(CalendarActivity.this, MainActivity.class);
                startActivity(home);
            }
        });
        footerCalendar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent calender = new Intent(CalendarActivity.this, CalendarActivity.class);
                startActivity(calender);
            }
        });
        footerTodo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent todo = new Intent(CalendarActivity.this, TodoActivity.class);
                startActivity(todo);
            }
        });
        footerImp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent imp = new Intent(CalendarActivity.this, ImpGoalsActivity.class);
                startActivity(imp);
            }
        });
        footerSched.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent sched = new Intent(CalendarActivity.this, ScheduleActivity.class);
                startActivity(sched);
            }
        });


        //Checks if a new day has been selected on the calendar
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                String date = day + "/" + (month+1) + "/" + year; //Gets a string format of the date selected to pass to db
                populateListView(date); //populates list with events from current selected day
            }
        });

        //Listens for the add event button being clicked. If it is takes user to add event activity.
        FloatingActionButton addEvent = findViewById(R.id.buttonAddEvent);
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addEvent = new Intent(CalendarActivity.this, AddEventActivity.class);
                startActivity(addEvent);
            }
        });
    }

    //Populates list view with events from selected day.
    private void populateListView(String findDate) {

        //Gets all events from a selected day and stores them in a database.
        ArrayList<Event> dbList = database.getEventData(findDate);

        //Uses the adapter to displays the data within the array list onto the list view.
        if(mAdapter==null) {
            mAdapter = new EventAdapter(this,dbList);
            eventList.setAdapter(mAdapter);
        }else{
            mAdapter.clear();
            mAdapter.addAll(dbList);
            mAdapter.notifyDataSetChanged();
        }

    }
}
