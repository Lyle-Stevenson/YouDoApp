package com.youdo.Schedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.youdo.Calendar.CalendarActivity;
import com.youdo.DatabaseHelper;
import com.youdo.ImportantDates.ImpGoalsActivity;
import com.youdo.MainActivity;
import com.youdo.R;
import com.youdo.TodoList.TodoActivity;

public class AddToScheduleActivity extends AppCompatActivity {

    private DatabaseHelper database; //referes to database
    EditText editName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_schedule);

        database = new DatabaseHelper(this); //Reference to db

        Button buttonHome = findViewById(R.id.buttonHome);
        Button footerCalendar = findViewById(R.id.footerCalendar);
        Button footerTodo = findViewById(R.id.footerTodo);
        Button footerImp = findViewById(R.id.footerImp);
        Button footerSched = findViewById(R.id.footerSched);

        //Header and footer intents.
        buttonHome.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent home = new Intent(AddToScheduleActivity.this, MainActivity.class);
                startActivity(home);
            }
        });
        footerCalendar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent calender = new Intent(AddToScheduleActivity.this, CalendarActivity.class);
                startActivity(calender);
            }
        });
        footerTodo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent todo = new Intent(AddToScheduleActivity.this, TodoActivity.class);
                startActivity(todo);
            }
        });
        footerImp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent imp = new Intent(AddToScheduleActivity.this, ImpGoalsActivity.class);
                startActivity(imp);
            }
        });
        footerSched.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
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

        //Fetches the shedule items data the was passed in the intent.
        String startTime = getIntent().getStringExtra("Start");
        String endTime = getIntent().getStringExtra("End");
        String day = getIntent().getStringExtra("Day");

        //Create new shedule item with the given data.
        ScheduleItem newItem = new ScheduleItem(name, startTime, endTime);

        //Updates shedule item in the database.
        database.updateScheduleItem(newItem, day);

        //Returns user to scheduler activity.
        Intent schedule = new Intent(AddToScheduleActivity.this, ScheduleActivity.class);
        startActivity(schedule);
    }
}
