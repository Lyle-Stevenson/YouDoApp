package com.youdo.Schedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
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

import com.youdo.Calendar.CalendarActivity;
import com.youdo.DatabaseHelper;
import com.youdo.ImportantDates.ImpGoalsActivity;
import com.youdo.MainActivity;
import com.youdo.R;
import com.youdo.TodoList.TodoActivity;

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

        //initalises the tablayout
        days = (TabLayout) findViewById(R.id.dayTabs);

        database = new DatabaseHelper(this);

        schedList = (ListView)findViewById(R.id.schedView);

        String day = days.getTabAt(days.getSelectedTabPosition()).getText().toString();

        Button buttonHome = findViewById(R.id.buttonHome);
        Button footerCalendar = findViewById(R.id.footerCalendar);
        Button footerTodo = findViewById(R.id.footerTodo);
        Button footerImp = findViewById(R.id.footerImp);
        Button footerSched = findViewById(R.id.footerSched);

        buttonHome.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creates and intent to change activity from main to calendar
                Intent home = new Intent(ScheduleActivity.this, MainActivity.class);
                startActivity(home);
            }
        });
        footerCalendar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creates and intent to change activity from main to calendar
                Intent calender = new Intent(ScheduleActivity.this, CalendarActivity.class);
                startActivity(calender);
            }
        });
        footerTodo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creates and intent to change activity from main to calendar
                Intent todo = new Intent(ScheduleActivity.this, TodoActivity.class);
                startActivity(todo);
            }
        });
        footerImp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creates and intent to change activity from main to calendar
                Intent imp = new Intent(ScheduleActivity.this, ImpGoalsActivity.class);
                startActivity(imp);
            }
        });
        footerSched.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creates and intent to change activity from main to calendar
                Intent sched = new Intent(ScheduleActivity.this, ScheduleActivity.class);
                startActivity(sched);
            }
        });

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

        if(id == R.id.homeButton){
            //Creates intent to start add task activity.
            Intent home = new Intent(ScheduleActivity.this, MainActivity.class);
            startActivity(home);
        }

        return  super.onOptionsItemSelected(item);
    }

}
