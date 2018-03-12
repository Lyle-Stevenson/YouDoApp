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
import android.widget.Spinner;

import java.util.ArrayList;

public class TodoActivity extends AppCompatActivity {

    //Change 1

    private DatabaseHelper database;

    private RecyclerView todoList; //Reference to the recycle view
    Context context = TodoActivity.this;
    private RecyclerView recyclerViewTodo;
    private ArrayList<Task> listTasks;
    private TaskRecyclerAdapter todoRecyclerAdapter;
    private ArrayList<Task> filteredList;
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

        //initialises the recycler view

        listTasks = new ArrayList<>();
        recyclerViewTodo = (RecyclerView) findViewById(R.id.todo_list);
        todoRecyclerAdapter = new TaskRecyclerAdapter(listTasks, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewTodo.setLayoutManager(mLayoutManager);
        recyclerViewTodo.setItemAnimator(new DefaultItemAnimator());
        recyclerViewTodo.setHasFixedSize(true);
        recyclerViewTodo.setAdapter(todoRecyclerAdapter);
        populateListView();


    }

    private void populateListView() {

        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                listTasks.clear();
                listTasks.addAll(database.getTaskData(selectedFilter));

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                todoRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();

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
