package com.youdo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;

public class AddtaskActivity extends AppCompatActivity {
    private FirebaseDatabase database; //referes to firebase database
    private DatabaseReference myRef; //Reference to the database
    EditText editTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);
        //Gets reference to the database
        database = FirebaseDatabase.getInstance();
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

        //Reference to the database holding the to do tasks
        myRef = database.getInstance().getReference().child("Todos");

        //Sets up push function to push task to database
        DatabaseReference newTodo = myRef.push();
        //Sets values for the to do task
        newTodo.child("name").setValue(name);
        newTodo.child("time").setValue(dateString);

    }
}
