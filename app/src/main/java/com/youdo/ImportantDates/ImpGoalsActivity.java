package com.youdo.ImportantDates;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.youdo.Calendar.CalendarActivity;
import com.youdo.DatabaseHelper;
import com.youdo.MainActivity;
import com.youdo.R;
import com.youdo.Schedule.ScheduleActivity;
import com.youdo.TodoList.AddTaskActivity;
import com.youdo.TodoList.TodoActivity;

import java.util.ArrayList;

public class ImpGoalsActivity extends AppCompatActivity {

    private SectionsPageAdapter mSectionsPageAdapter;
    private DatabaseHelper database;
    private ViewPager mViewPager;
    ListView goalList;
    ArrayAdapter<String> goalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imp_goals);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        goalList = (ListView) findViewById(R.id.goalsList);

        database = new DatabaseHelper(this);

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        Button buttonHome = findViewById(R.id.buttonHome);
        Button footerCalendar = findViewById(R.id.footerCalendar);
        Button footerTodo = findViewById(R.id.footerTodo);
        Button footerImp = findViewById(R.id.footerImp);
        Button footerSched = findViewById(R.id.footerSched);

        buttonHome.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creates and intent to change activity from main to calendar
                Intent home = new Intent(ImpGoalsActivity.this, MainActivity.class);
                startActivity(home);
            }
        });
        footerCalendar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creates and intent to change activity from main to calendar
                Intent calender = new Intent(ImpGoalsActivity.this, CalendarActivity.class);
                startActivity(calender);
            }
        });
        footerTodo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creates and intent to change activity from main to calendar
                Intent todo = new Intent(ImpGoalsActivity.this, TodoActivity.class);
                startActivity(todo);
            }
        });
        footerImp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creates and intent to change activity from main to calendar
                Intent imp = new Intent(ImpGoalsActivity.this, ImpGoalsActivity.class);
                startActivity(imp);
            }
        });
        footerSched.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creates and intent to change activity from main to calendar
                Intent sched = new Intent(ImpGoalsActivity.this, ScheduleActivity.class);
                startActivity(sched);
            }
        });
        //populateGoalView();
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new GoalsFragment(),"Goals");
        adapter.addFragment(new DatesFragment(),"Important dates");

        viewPager.setAdapter(adapter);
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

        if(id == R.id.homeButton){
            //Creates intent to start add task activity.
            Intent home = new Intent(ImpGoalsActivity.this, MainActivity.class);
            startActivity(home);
        }

        return  super.onOptionsItemSelected(item);
    }
}
