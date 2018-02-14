package com.youdo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;

public class AddtaskActivity extends AppCompatActivity {
    private DatabaseHelper database; //referes to database
    EditText editTask;
    //Hi Lyle, android studio is working

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);
        //Gets reference to the database
        database = new DatabaseHelper(this);
    }
    //When add task buttion is clicked
    public void addButtonClicked(View view) {

        //adds reference to the text box on activity
        editTask = (EditText) findViewById(R.id.editTask);

        //Returns the name to the text in the box
        String name = editTask.getText().toString();

        //Gets the time that the task is added
        long date = System.currentTimeMillis();

        //Sets up a date format YYYY MM DD
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyy h:mm a");

        //transforms the system time taken into the fate format
        String dateString = sdf.format(date);

        //Puts new task data into a to do object
        Todo newTask = new Todo(name,dateString);

        database.addToDo(newTask);// Adds task to database

        //Switches back to the to do activity
        Intent TaskList = new Intent( AddtaskActivity.this, TodoActivity.class);
        startActivity(TaskList);
    }
}
