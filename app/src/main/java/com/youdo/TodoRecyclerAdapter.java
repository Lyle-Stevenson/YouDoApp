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
 */

public class TodoRecyclerAdapter extends RecyclerView.Adapter<TodoRecyclerAdapter.TodoViewHolder> {

    private ArrayList<Todo> listTasks;
    private Context mContext;
    private ArrayList<Todo> mFilteredList;


    public TodoRecyclerAdapter(ArrayList<Todo> listTasks, Context mContext) {
        this.listTasks = listTasks;
        this.mContext = mContext;


    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {

        TextView todo_name;
        TextView todo_date;

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
        Log.d("List", listTasks.get(position).toString());
        holder.todo_name.setText(listTasks.get(position).getName());
        holder.todo_date.setText(listTasks.get(position).getDate());
    }


    @Override
    public int getItemCount() {
        return listTasks.size();
    }
}