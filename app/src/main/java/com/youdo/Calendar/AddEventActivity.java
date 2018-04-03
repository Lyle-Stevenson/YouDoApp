package com.youdo.Calendar;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.youdo.AlarmReceiver;
import com.youdo.DatabaseHelper;
import com.youdo.ImportantDates.ImpGoalsActivity;
import com.youdo.MainActivity;
import com.youdo.R;
import com.youdo.Schedule.ScheduleActivity;
import com.youdo.TodoList.AddTaskActivity;
import com.youdo.TodoList.TodoActivity;

import java.util.Calendar;

public class AddEventActivity extends AppCompatActivity {

    private DatabaseHelper database; //referes to database
    EditText editName;
    EditText editDescription;
    Button buttonDate, buttonTime;
    EditText editDate, editTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    Calendar calendarReminder = Calendar.getInstance();



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

        Button buttonHome = findViewById(R.id.buttonHome);
        Button footerCalendar = findViewById(R.id.footerCalendar);
        Button footerTodo = findViewById(R.id.footerTodo);
        Button footerImp = findViewById(R.id.footerImp);
        Button footerSched = findViewById(R.id.footerSched);

        buttonHome.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creates and intent to change activity from main to calendar
                Intent home = new Intent(AddEventActivity.this, MainActivity.class);
                startActivity(home);
            }
        });
        footerCalendar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creates and intent to change activity from main to calendar
                Intent calender = new Intent(AddEventActivity.this, CalendarActivity.class);
                startActivity(calender);
            }
        });
        footerTodo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creates and intent to change activity from main to calendar
                Intent todo = new Intent(AddEventActivity.this, TodoActivity.class);
                startActivity(todo);
            }
        });
        footerImp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creates and intent to change activity from main to calendar
                Intent imp = new Intent(AddEventActivity.this, ImpGoalsActivity.class);
                startActivity(imp);
            }
        });
        footerSched.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creates and intent to change activity from main to calendar
                Intent sched = new Intent(AddEventActivity.this, ScheduleActivity.class);
                startActivity(sched);
            }
        });

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
                        calendarReminder.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        calendarReminder.set(Calendar.MONTH,monthOfYear);

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
                        calendarReminder.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendarReminder.set(Calendar.MINUTE,minute);

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
        editDate = (EditText) findViewById(R.id.editEventDate);

        //Returns the name to the text in the box
        String date = editDate.getText().toString();

        //adds reference to the text box on activity
        editTime = (EditText) findViewById(R.id.editEventTime);

        //Returns the name to the text in the box
        String time = editTime.getText().toString();

        Event newEvent = new Event(name, date, time);

        database.addEvent(newEvent);

        setAlarm(calendarReminder.getTimeInMillis(), newEvent);

        Intent Calendar = new Intent(AddEventActivity.this, CalendarActivity.class);
        startActivity(Calendar);
    }

    private void setAlarm(long timeInMillis,Event event){

        Log.d("Time", "hi" + timeInMillis);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        int id = (int) System.currentTimeMillis();

        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        intent.putExtra("Name", event.getName());
        intent.putExtra("Id", id);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),id,intent,0);


        alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent);
    }

}