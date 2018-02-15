package com.youdo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;

public class AddEventActivity extends AppCompatActivity {

    private DatabaseHelper database; //referes to database
    EditText editName;
    EditText editDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        //Gets reference to the database
        database = new DatabaseHelper(this);

    }


    //When add event buttion is clicked

    public void addButtonClicked(View view) {

        //adds reference to the text box on activity
        editName = (EditText) findViewById(R.id.editName);

        //Returns the name to the text in the box
        String name = editName.getText().toString();

        //adds reference to the text box on activity
        editDescription = (EditText) findViewById(R.id.editDescription);

        //Returns the name to the text in the box
        String description = editDescription.getText().toString();


        Event newEvent = new Event(name, description);

        database.addEvent(newEvent);

        Intent Calendar = new Intent(AddEventActivity.this, CalendarActivity.class);
        startActivity(Calendar);
    }

}
