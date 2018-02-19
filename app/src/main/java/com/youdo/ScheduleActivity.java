package com.youdo;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity {

    private DatabaseHelper database;

    private RecyclerView todoList; //Reference to the recycle view
    Context context = ScheduleActivity.this;
    private RecyclerView recyclerViewSchedule;
    private ArrayList<Event> listSchedule;
    private EventRecyclerAdapter scheduleRecyclerAdapter;
    TabLayout days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        days = (TabLayout) findViewById(R.id.dayTabs);
        setSupportActionBar(toolbar);

        listSchedule = new ArrayList<>();//Array list to hold fetched items

        // Initialises the recyclerView
        recyclerViewSchedule = (RecyclerView) findViewById(R.id.schedRecyclerView);
        scheduleRecyclerAdapter = new EventRecyclerAdapter(listSchedule, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewSchedule.setLayoutManager(mLayoutManager);
        recyclerViewSchedule.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSchedule.setHasFixedSize(true);
        recyclerViewSchedule.setAdapter(scheduleRecyclerAdapter);

    }

    private void populateListView(final String findDate) {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listSchedule.clear(); //Clear the current data that has been taken from db
                //We have to find a way to pass what day tab we are in
                // listSchedule.addAll(database.getScheduleData()); //Get up to date data from db

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                scheduleRecyclerAdapter.notifyDataSetChanged();
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
            Intent addScheduleItem = new Intent(ScheduleActivity.this, AddToScheduleActivity.class);
            startActivity(addScheduleItem);
        }

        return  super.onOptionsItemSelected(item);
    }

}
