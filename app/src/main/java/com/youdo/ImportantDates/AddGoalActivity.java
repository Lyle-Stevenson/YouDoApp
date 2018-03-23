package com.youdo.ImportantDates;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.youdo.DatabaseHelper;
import com.youdo.R;
import com.youdo.Schedule.AddToScheduleActivity;
import com.youdo.Schedule.ScheduleActivity;
import com.youdo.Schedule.ScheduleItem;

import java.util.Calendar;

/**
 * Created by Lyle on 23/03/2018.
 */

public class AddGoalActivity  extends AppCompatActivity {

    private DatabaseHelper database; //referes to database
    EditText editName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);

        database = new DatabaseHelper(this);

    }

    public void addGoalButtonClicked(View view) {
        //adds reference to the text box on activity
        editName = (EditText) findViewById(R.id.editGoalName);

        //Returns the name to the text in the box
        String name = editName.getText().toString();

        database.addGoal(name);

        Log.d("Goals" , "Goal added: " + name);
        Intent goals = new Intent(AddGoalActivity.this, ImpGoalsActivity.class);
        startActivity(goals);
    }
}

