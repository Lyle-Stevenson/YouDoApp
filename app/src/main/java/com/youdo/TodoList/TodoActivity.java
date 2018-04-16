package com.youdo.TodoList;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.youdo.Calendar.CalendarActivity;
import com.youdo.DatabaseHelper;
import com.youdo.ImportantDates.AddGoalActivity;
import com.youdo.ImportantDates.ImpGoalsActivity;
import com.youdo.MainActivity;
import com.youdo.R;
import com.youdo.Schedule.ScheduleActivity;

import java.util.ArrayList;

public class TodoActivity extends AppCompatActivity {

    private DatabaseHelper database;
    ListView taskList;
    ArrayAdapter<Task> mAdapter;
    Spinner categoryFilter;
    String selectedFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        //Create reference to database
        database = new DatabaseHelper(this);

        //link list view to variable/
        taskList = (ListView) findViewById(R.id.todo_list);

        //Creates the dropdown menu for the filter
        categoryFilter= findViewById(R.id.filter_Drop_Down);


        //fills drop down list with categories fetched from database.
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(TodoActivity.this,
                android.R.layout.simple_list_item_1, database.getCat());
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryFilter.setAdapter(myAdapter);

        Button buttonHome = findViewById(R.id.buttonHome);
        Button footerCalendar = findViewById(R.id.footerCalendar);
        Button footerTodo = findViewById(R.id.footerTodo);
        Button footerImp = findViewById(R.id.footerImp);
        Button footerSched = findViewById(R.id.footerSched);

        //Header and footer intents.
        buttonHome.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent home = new Intent( TodoActivity.this, MainActivity.class);
                startActivity(home);
            }
        });
        footerCalendar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent calender = new Intent(TodoActivity.this, CalendarActivity.class);
                startActivity(calender);
            }
        });
        footerTodo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent todo = new Intent(TodoActivity.this, TodoActivity.class);
                startActivity(todo);
            }
        });
        footerImp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent imp = new Intent(TodoActivity.this, ImpGoalsActivity.class);
                startActivity(imp);
            }
        });
        footerSched.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent sched = new Intent(TodoActivity.this, ScheduleActivity.class);
                startActivity(sched);
            }
        });

        //Button to take user to add goal page.
        FloatingActionButton addGoal = findViewById(R.id.buttonAddTask);
        addGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTask = new Intent(TodoActivity.this, AddTaskActivity.class);
                startActivity(addTask);
            }
        });

        //Listens for the category filter being changed.
        categoryFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            //if a new filter is selected.
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedFilter = adapterView.getSelectedItem().toString(); //Change variable to the current selected filter.
                populateListView(); //Populate view with new filter.
            }

            @Override
            //impossible to not select
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        populateListView();//Call populate list view.
    }

    //Populates the task list view with current filter.
    private void populateListView() {

        //Fetched all task from the database
        ArrayList<Task> dbList = database.getTaskData(selectedFilter);
        //displayes new set of task data fetched on the list view.
        if(mAdapter==null) {
           mAdapter = new TaskAdapter(this,dbList);
           taskList.setAdapter(mAdapter);
        }else{
            mAdapter.clear();
            mAdapter.addAll(dbList);
            mAdapter.notifyDataSetChanged();
        }

    }

    //If the delete button for a task is clicked.
    public void deleteTaskButtonClicked(View view){
        View parent = (View)view.getParent();
        //Stores the task name of deleted task.
        TextView taskName = (TextView)parent.findViewById(R.id.todoName);
        String task = String.valueOf(taskName.getText());
        //Calls delete function in database
        database.deleteTodo(task);
        //repopulated the listview without the deleted item.
        populateListView();
    }

}
