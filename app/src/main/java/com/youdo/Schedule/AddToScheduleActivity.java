package com.youdo.Schedule;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.youdo.Calendar.CalendarActivity;
import com.youdo.DatabaseHelper;
import com.youdo.ImportantDates.ImpGoalsActivity;
import com.youdo.MainActivity;
import com.youdo.R;
import com.youdo.TodoList.TodoActivity;

import java.util.Calendar;

public class AddToScheduleActivity extends AppCompatActivity {

    private DatabaseHelper database; //referes to database
    EditText editName;
    Button buttonEndTime, buttonStartTime;
    EditText editEndTime, editStartTime;
    private int endHour, endMinute, startHour, startMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_schedule);

        database = new DatabaseHelper(this);

        Button buttonHome = findViewById(R.id.buttonHome);
        Button footerCalendar = findViewById(R.id.footerCalendar);
        Button footerTodo = findViewById(R.id.footerTodo);
        Button footerImp = findViewById(R.id.footerImp);
        Button footerSched = findViewById(R.id.footerSched);

        buttonHome.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creates and intent to change activity from main to calendar
                Intent home = new Intent(AddToScheduleActivity.this, MainActivity.class);
                startActivity(home);
            }
        });
        footerCalendar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creates and intent to change activity from main to calendar
                Intent calender = new Intent(AddToScheduleActivity.this, CalendarActivity.class);
                startActivity(calender);
            }
        });
        footerTodo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creates and intent to change activity from main to calendar
                Intent todo = new Intent(AddToScheduleActivity.this, TodoActivity.class);
                startActivity(todo);
            }
        });
        footerImp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creates and intent to change activity from main to calendar
                Intent imp = new Intent(AddToScheduleActivity.this, ImpGoalsActivity.class);
                startActivity(imp);
            }
        });
        footerSched.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creates and intent to change activity from main to calendar
                Intent sched = new Intent(AddToScheduleActivity.this, ScheduleActivity.class);
                startActivity(sched);
            }
        });
    }

    public void addScheduleButtonClicked(View view) {
        //adds reference to the text box on activity
        editName = (EditText) findViewById(R.id.editScheduleName);

        //Returns the name to the text in the box
        String name = editName.getText().toString();

        //Returns the name to the text in the box
        String startTime = getIntent().getStringExtra("Start");

        //Returns the name to the text in the box
        String endTime = getIntent().getStringExtra("End");

        ScheduleItem newItem = new ScheduleItem(name, startTime, endTime);

        String day = getIntent().getStringExtra("Day");// Fetechs the day data passed in intent

        database.updateScheduleItem(newItem, day);

        Intent schedule = new Intent(AddToScheduleActivity.this, ScheduleActivity.class);
        startActivity(schedule);
    }
}
