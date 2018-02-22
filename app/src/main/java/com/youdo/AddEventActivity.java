package com.youdo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddEventActivity extends AppCompatActivity {

    private DatabaseHelper database; //referes to database
    EditText editName;
    EditText editDescription;
    Button buttonDate, buttonTime;
    EditText editDate, editTime;
    private int mYear, mMonth, mDay, mHour, mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        //Gets reference to the database
        database = new DatabaseHelper(this);

        buttonDate=(Button)findViewById(R.id.buttonEventDate);
        buttonTime=(Button)findViewById(R.id.buttonEventTime);
        editDate=(EditText)findViewById(R.id.editEventDate);
        editTime=(EditText)findViewById(R.id.editEventTime);
    }

    public void setEventDateClicked(View view){
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        editDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void setEventTimeClicked(View view){

        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        editTime.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }
    //When add event buttion is clicked

    public void addEventButtonClicked(View view) {

        //adds reference to the text box on activity
        editName = (EditText) findViewById(R.id.editEventName);

        //Returns the name to the text in the box
        String name = editName.getText().toString();

        //adds reference to the text box on activity
        editDescription = (EditText) findViewById(R.id.editEventDescription);

        //Returns the name to the text in the box
        String description = editDescription.getText().toString();

        //adds reference to the text box on activity
        editDate = (EditText) findViewById(R.id.editEventDate);

        //Returns the name to the text in the box
        String date = editDate.getText().toString();

        //adds reference to the text box on activity
        editTime = (EditText) findViewById(R.id.editEventTime);

        //Returns the name to the text in the box
        String time = editTime.getText().toString();

        Event newEvent = new Event(name, description, date, time);

        database.addEvent(newEvent);

        Intent Calendar = new Intent(AddEventActivity.this, CalendarActivity.class);
        startActivity(Calendar);
    }

}
