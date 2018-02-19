package com.youdo;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

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

        buttonEndTime=(Button)findViewById(R.id.buttonEndTime);
        buttonStartTime=(Button)findViewById(R.id.buttonStartTime);
        editStartTime=(EditText)findViewById(R.id.editStartTime);
        editEndTime=(EditText)findViewById(R.id.editEndTime);
    }

    public void setEndTimeClicked(View view) {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        endHour = c.get(Calendar.HOUR_OF_DAY);
        endMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        editEndTime.setText(hourOfDay + ":" + minute);
                    }
                }, endHour, endMinute, false);
        timePickerDialog.show();
    }

    public void setStartTimeClicked(View view) {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        startHour = c.get(Calendar.HOUR_OF_DAY);
        startMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        editStartTime.setText(hourOfDay + ":" + minute);
                    }
                }, startHour, startMinute, false);
        timePickerDialog.show();
    }

    public void addButtonClicked(View view) {
        //adds reference to the text box on activity
        editName = (EditText) findViewById(R.id.editName);

        //Returns the name to the text in the box
        String name = editName.getText().toString();

        //adds reference to the text box on activity
        editStartTime = (EditText) findViewById(R.id.editStartTime);

        //Returns the name to the text in the box
        String startTime = editStartTime.getText().toString();

        //adds reference to the text box on activity
        editEndTime = (EditText) findViewById(R.id.editEndTime);

        //Returns the name to the text in the box
        String endTime = editEndTime.getText().toString();

        ScheduleItem newItem = new ScheduleItem(name, startTime, endTime);

        //We need to find a way to determin which day is being added to and pass it to the database.
       //database.addScheduleItem(newItem, );

        Intent Calendar = new Intent(AddToScheduleActivity.this, ScheduleActivity.class);
        startActivity(Calendar);
    }
}
