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
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity {

    private DatabaseHelper database;
    ListView schedList;
    ArrayAdapter<ScheduleItem> mAdapter;
    private CalendarView calendarView; //Reference to the calendar view.
    TabLayout days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //initalises the tablayout
        days = (TabLayout) findViewById(R.id.dayTabs);

        database = new DatabaseHelper(this);

        schedList = (ListView)findViewById(R.id.schedView);

        String day = days.getTabAt(days.getSelectedTabPosition()).getText().toString();

        days.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String daySelected = days.getTabAt(days.getSelectedTabPosition()).getText().toString();
                populateListView(daySelected);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        populateListView(day);
    }

    private void populateListView(String day) {

        Log.d("", day);
        ArrayList<ScheduleItem> dbList = database.getScheduleData(day);
        if(mAdapter==null) {
            mAdapter = new ScheduleAdapter(this,dbList);
            schedList.setAdapter(mAdapter);
        }else{
            mAdapter.clear();
            mAdapter.addAll(dbList);
            mAdapter.notifyDataSetChanged();
        }
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
            int pos = days.getSelectedTabPosition();
            Intent addScheduleItem = new Intent(ScheduleActivity.this, AddToScheduleActivity.class);
            addScheduleItem.putExtra("Day", days.getTabAt(pos).getText().toString()); //Passes the current day seleceted data in the intent to add schedule activity.
            startActivity(addScheduleItem);
        }

        return  super.onOptionsItemSelected(item);
    }

}
