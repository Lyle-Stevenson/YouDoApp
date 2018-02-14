package com.youdo;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class TodoActivity extends AppCompatActivity {

    private DatabaseHelper database;

    private RecyclerView todoList; //Reference to the recycle view
    Context context = TodoActivity.this;
    private RecyclerView recyclerViewTodo;
    private ArrayList<Todo> listTasks;
    private TodoRecyclerAdapter todoRecyclerAdapter;
    private ArrayList<Todo> filteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        //Adds the toolbar to activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        database = new DatabaseHelper(this);

        //initialises the recycler view

        listTasks = new ArrayList<>();
        recyclerViewTodo = (RecyclerView) findViewById(R.id.todo_list);
        todoRecyclerAdapter = new TodoRecyclerAdapter(listTasks, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewTodo.setLayoutManager(mLayoutManager);
        recyclerViewTodo.setItemAnimator(new DefaultItemAnimator());
        recyclerViewTodo.setHasFixedSize(true);
        recyclerViewTodo.setAdapter(todoRecyclerAdapter);

        populateListView();
    }

    public static class TodoViewHolder extends RecyclerView.ViewHolder {

        public TodoViewHolder(View itemView) {
            super(itemView);
            View mView = itemView;
        }

        public void setName(String name) {
            TextView todo_name = (TextView) itemView.findViewById(R.id.todoName);
            todo_name.setText(name);
        }

        public void setCat(String cat) {
            TextView todo_cat = (TextView) itemView.findViewById(R.id.todoCategory);
            todo_cat.setText(cat);
        }

        public void setDate(String date) {
            TextView todo_date = (TextView) itemView.findViewById(R.id.todoDate);
            todo_date.setText(date);
        }
    }

    private void populateListView() {

        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listTasks.clear();
                listTasks.addAll(database.getTaskData());

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
                Intent addTask = new Intent(TodoActivity.this, AddtaskActivity.class);
                startActivity(addTask);
            }

            return  super.onOptionsItemSelected(item);
        }

}
