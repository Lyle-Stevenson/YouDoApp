package com.youdo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TodoActivity extends AppCompatActivity {
//testdfsfsdfs
    private RecyclerView todoList; //Reference to the recycle viewfds
    private DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        //Adds the toolbar to activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initialises the recycler view
        todoList = (RecyclerView) findViewById(R.id.todo_list);
        todoList.setHasFixedSize(true);
        todoList.setLayoutManager(new LinearLayoutManager(this));
        //Reference to the database table to dos
        database = FirebaseDatabase.getInstance().getReference().child("Todos");
    }

    @Override
        public boolean onCreateOptionsMenu(Menu menu){
            getMenuInflater().inflate(R.menu.menu_todo,menu);
            return true;
        }

    //New class to assamble the list view
    public static class TodoViewHolder extends RecyclerView.ViewHolder {

        public TodoViewHolder(View itemView) {
            super(itemView);
            View mView = itemView;
        }

        public void setName(String name) {
            TextView todo_name = (TextView) itemView.findViewById(R.id.todoName);
            todo_name.setText(name);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Not really sure exactly basically links the firebase with the recyclervieew
        FirebaseRecyclerAdapter<Todo,TodoViewHolder> FBRA = new FirebaseRecyclerAdapter<Todo, TodoViewHolder>(Todo.class,R.layout.task_row,TodoViewHolder.class,database){

            @Override //Populates the view with the database tasks
            protected void populateViewHolder(TodoViewHolder viewHolder, Todo model, int position) {
                viewHolder.setName(model.getName());
            }
        };
        todoList.setAdapter(FBRA);
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
