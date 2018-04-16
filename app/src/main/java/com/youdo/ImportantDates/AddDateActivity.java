package com.youdo.ImportantDates;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.youdo.Calendar.AddEventActivity;
import com.youdo.Calendar.CalendarActivity;
import com.youdo.Calendar.Event;
import com.youdo.DatabaseHelper;
import com.youdo.MainActivity;
import com.youdo.R;
import com.youdo.Schedule.ScheduleActivity;
import com.youdo.TodoList.TodoActivity;

import java.util.Calendar;

public class AddDateActivity extends AppCompatActivity {

    private DatabaseHelper database; //referes to database
    EditText editName;
    Button buttonDate;
    EditText editDate;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_date);

        database = new DatabaseHelper(this);

        buttonDate=(Button)findViewById(R.id.buttonDateDate);
        editDate=(EditText)findViewById(R.id.editDateDate);

        Button buttonHome = findViewById(R.id.buttonHome);
        Button footerCalendar = findViewById(R.id.footerCalendar);
        Button footerTodo = findViewById(R.id.footerTodo);
        Button footerImp = findViewById(R.id.footerImp);
        Button footerSched = findViewById(R.id.footerSched);

        //Header and footer intents.
        buttonHome.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent home = new Intent(AddDateActivity.this, MainActivity.class);
                startActivity(home);
            }
        });
        footerCalendar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent calender = new Intent(AddDateActivity.this, CalendarActivity.class);
                startActivity(calender);
            }
        });
        footerTodo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent todo = new Intent(AddDateActivity.this, TodoActivity.class);
                startActivity(todo);
            }
        });
        footerImp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent imp = new Intent(AddDateActivity.this, ImpGoalsActivity.class);
                startActivity(imp);
            }
        });
        footerSched.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent sched = new Intent(AddDateActivity.this, ScheduleActivity.class);
                startActivity(sched);
            }
        });

    }

    public void setDateDateClicked(View view){
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) { //Creates a date picker dialog with at current date.
                editDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year); //Sets the selected date in the text box.
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    //When the new important date is added.
    public void addImpDateButtonClicked(View view) {

        //adds reference to the text box on activity
        editName = (EditText) findViewById(R.id.editDateName);

        //Returns the name to the text in the box
        String name = editName.getText().toString();

        //adds reference to the text box on activity
        editDate = (EditText) findViewById(R.id.editDateDate);

        //Returns the date to the text in the box
        String date = editDate.getText().toString();

        //Creates a new date object using new data.
        Date newDate = new Date(name, date);

        //Adds date to database.
        database.addDate(newDate);

        //Creates and intent to take user back to the previous activity.
        Intent Date = new Intent(AddDateActivity.this, ImpGoalsActivity.class);
        startActivity(Date);
    }
}
