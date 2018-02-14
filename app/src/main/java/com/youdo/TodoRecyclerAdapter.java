package com.youdo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Lyle on 12/02/2018.
 * Recycler adapter connects the task data to the recycler view
 */

public class TodoRecyclerAdapter extends RecyclerView.Adapter<TodoRecyclerAdapter.TodoViewHolder> {

    private ArrayList<Todo> listTasks;  //List of tasks to be displayed
    private Context mContext; //Connect of which activity the data is to be displayed on

    //Construcor
    public TodoRecyclerAdapter(ArrayList<Todo> listTasks, Context mContext) {
        this.listTasks = listTasks;
        this.mContext = mContext;
    }

    //View holder class which hold where the data is to be displayed
    public class TodoViewHolder extends RecyclerView.ViewHolder {

        TextView todo_name;
        TextView todo_date;

        //Constructor to hold the view elements
        public TodoViewHolder(View view) {
            super(view);
            todo_name = (TextView) itemView.findViewById(R.id.todoName);
            todo_date = (TextView) itemView.findViewById(R.id.todoDate);
        }


    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_row,parent,false);

        return new TodoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final TodoViewHolder holder, int position) {
        holder.todo_name.setText(listTasks.get(position).getName());// Sets the text for name in the name element on the view
        holder.todo_date.setText(listTasks.get(position).getDate());// Sets the text for date in the date element on the view
    }


    @Override
    public int getItemCount() {
        return listTasks.size();
    }
}