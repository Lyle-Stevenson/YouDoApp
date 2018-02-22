package com.youdo;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.util.ArrayList;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    private DatabaseHelper database;

    private RecyclerView todoList; //Reference to the recycle view
    Context context = CalendarActivity.this;
    private RecyclerView recyclerViewEvent; //Reference to the recycler view
    private ArrayList<Event> listEvents; // List to store the events fetched from the db
    private EventRecyclerAdapter eventRecyclerAdapter; // Reference to recycler view adapter
    private CalendarView calendarView; //Reference to the calendar view.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        //Adds the toolbar to activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        database = new DatabaseHelper(this);//Database reference


        //initialises the recycler view

        listEvents = new ArrayList<>();
        recyclerViewEvent = (RecyclerView) findViewById(R.id.eventView);
        eventRecyclerAdapter = new EventRecyclerAdapter(listEvents, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewEvent.setLayoutManager(mLayoutManager);
        recyclerViewEvent.setItemAnimator(new DefaultItemAnimator());
        recyclerViewEvent.setHasFixedSize(true);
        recyclerViewEvent.setAdapter(eventRecyclerAdapter);

        calendarView = (CalendarView) findViewById(R.id.calendarView);

        //Checks if a new day has been selected on the calendar
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                String date = day + "/" + (month+1) + "/" + year; //Gets a string format of the date selected to pass to db
                populateListView(date); //poplates list with events from current day
            }
        });

    }

    private void populateListView(final String findDate) {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listEvents.clear();
                listEvents.addAll(database.getEventData(findDate)); //Gets the data for the current date

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                eventRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_todo,menu);
        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //Returns the selected action from action bar.
        int id = item.getItemId();
        //If the add task item is selected
        if(id == R.id.addTask){
            //Creates intent to start add task activity.
            Intent addEvent = new Intent(CalendarActivity.this, AddEventActivity.class);
            startActivity(addEvent);
        }

        return  super.onOptionsItemSelected(item);
    }
}
