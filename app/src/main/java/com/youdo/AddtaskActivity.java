package com.youdo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class AddtaskActivity extends AppCompatActivity {
    private DatabaseHelper database; //referes to database
    EditText editTask;
    Spinner categoryChoice;
    String selectedCategory;
    //Hi Lyle, android studio is working

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);
        //Gets reference to the database
        database = new DatabaseHelper(this);

        //creates spinner drop down for adding tasks to todo list
        categoryChoice = findViewById(R.id.category_drop_down);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(AddtaskActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.categories));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryChoice.setAdapter(myAdapter);

        categoryChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            //makes the choice in the dropdown menu = selectedCategory
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCategory = adapterView.getSelectedItem().toString();
            }

            @Override
            //impossible to not select
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
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
        SimpleDateFormat sdf = new SimpleDateFormat("MMM MM dd, yyy h :mm a");

        //transforms the system time taken into the fate format
        String dateString = sdf.format(date);

        Todo newTask = new Todo(name, selectedCategory, dateString);

        database.addToDo(newTask);

        Intent TaskList = new Intent(AddtaskActivity.this, TodoActivity.class);
        startActivity(TaskList);
    }

}

