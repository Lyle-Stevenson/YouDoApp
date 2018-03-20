package com.youdo;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class TodoActivity extends AppCompatActivity {

    //Change 1

    private DatabaseHelper database;
    ListView taskList;
    ArrayAdapter<Task> mAdapter;
    Spinner categoryFilter;
    String selectedFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        //Adds the toolbar to activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        database = new DatabaseHelper(this);

        taskList = (ListView) findViewById(R.id.todo_list);

        //Creates the dropdown menu for the filter
        categoryFilter= findViewById(R.id.filter_Drop_Down);

        //fills drop down list with categories
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(TodoActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.categories));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryFilter.setAdapter(myAdapter);

        categoryFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            //makes the choice in the dropdown menu = selectedFilter
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedFilter = adapterView.getSelectedItem().toString();
                Log.d("lol",selectedFilter);
                populateListView();
                //fixed by benjamin after Callum spent 15 hours trying to work out this stupid ass
                    //line of shitty code that took benjamin 30seconds to realise how retarded i was being

            }

            @Override
            //impossible to not select
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
        populateListView();


    }

    private void populateListView() {

        ArrayList<Task> dbList = database.getTaskData(selectedFilter);
        if(mAdapter==null) {
           mAdapter = new TaskAdapter(this,dbList);
           taskList.setAdapter(mAdapter);
        }else{
            mAdapter.clear();
            mAdapter.addAll(dbList);
            mAdapter.notifyDataSetChanged();
        }

    }

    public void deleteTaskButtonClicked(View view){
        View parent = (View)view.getParent();
        TextView taskName = (TextView)findViewById(R.id.todoName);
        String task = String.valueOf(taskName.getText());
        database.deleteTodo(task);
        populateListView();
    }

    @Override
        public boolean onCreateOptionsMenu(Menu menu){
            getMenuInflater().inflate(R.menu.menu_todo,menu);
            return true;
        }


    @Override
    protected void onStart() {
        super.onStart();

    }

        @Override
        public boolean onOptionsItemSelected(MenuItem item){
            //Returns the selected action from action bar.
            int id = item.getItemId();
            //If the add task item is selected
            if(id == R.id.addTask){
                //Creates intent to start add task activity.
                Intent addTask = new Intent(TodoActivity.this, AddTaskActivity.class);
                startActivity(addTask);
            }

            return  super.onOptionsItemSelected(item);
        }

}
