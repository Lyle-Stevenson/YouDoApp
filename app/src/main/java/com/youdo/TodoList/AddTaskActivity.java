package com.youdo.TodoList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.youdo.Calendar.CalendarActivity;
import com.youdo.DatabaseHelper;
import com.youdo.ImportantDates.ImpGoalsActivity;
import com.youdo.MainActivity;
import com.youdo.R;
import com.youdo.Schedule.ScheduleActivity;

import java.text.SimpleDateFormat;

public class AddTaskActivity extends AppCompatActivity {

    private DatabaseHelper database;
    EditText editTask;
    EditText editCat;
    Spinner categoryChoice;
    String selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        //Gets reference to the database
        database = new DatabaseHelper(this);

        //creates spinner drop down for adding tasks to to do list
        categoryChoice = findViewById(R.id.category_drop_down);

        //Populates dropdown with categories fetched from database.
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(AddTaskActivity.this,
                android.R.layout.simple_list_item_1, database.getCat());
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryChoice.setAdapter(myAdapter);

        Button buttonHome = findViewById(R.id.buttonHome);
        Button footerCalendar = findViewById(R.id.footerCalendar);
        Button footerTodo = findViewById(R.id.footerTodo);
        Button footerImp = findViewById(R.id.footerImp);
        Button footerSched = findViewById(R.id.footerSched);

        //Footer and header intents.
        buttonHome.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent home = new Intent(AddTaskActivity.this, MainActivity.class);
                startActivity(home);
            }
        });
        footerCalendar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent calender = new Intent(AddTaskActivity.this, CalendarActivity.class);
                startActivity(calender);
            }
        });
        footerTodo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent todo = new Intent(AddTaskActivity.this, TodoActivity.class);
                startActivity(todo);
            }
        });
        footerImp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent imp = new Intent(AddTaskActivity.this, ImpGoalsActivity.class);
                startActivity(imp);
            }
        });
        footerSched.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent sched = new Intent(AddTaskActivity.this, ScheduleActivity.class);
                startActivity(sched);
            }
        });

        //When category has been selected in dropdown.
        categoryChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            //makes the choice in the dropdown menu = selectedCategory
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCategory = adapterView.getSelectedItem().toString(); //Sets slected category variable to newly selected category.
            }

            @Override
            //impossible to not select
            public void onNothingSelected(AdapterView<?> adapterView) {}

        });
    }


    //When add task button is clicked
    public void addTaskButtonClicked(View view) {

        //adds reference to the text box on activity
        editTask = (EditText) findViewById(R.id.editTaskName);
        editCat = (EditText) findViewById(R.id.editTaskCat);

        //Returns the name to the text in the box
        String name = editTask.getText().toString();

        //Gets the time that the task is added
        long date = System.currentTimeMillis();

        //Sets up a date format YYYY MM DD
        SimpleDateFormat sdf = new SimpleDateFormat("MMM MM dd, yyy h :mm a");

        //transforms the system time taken into the fate format
        String dateString = sdf.format(date);

        String newCat = editCat.getText().toString();

        Task newTask = null;

        if(newCat.isEmpty()) {//If an existing category was sleeted a task object is created with that category.
            newTask = new Task(name, selectedCategory, dateString);
        }else{ //Else its created with the newly entered category.
            newTask = new Task(name, newCat, dateString);
        }

        //Adds new task to database
        database.addToDo(newTask);

        //Takes user back to the to do activity.
        Intent TaskList = new Intent(AddTaskActivity.this, TodoActivity.class);
        startActivity(TaskList);
    }

}

