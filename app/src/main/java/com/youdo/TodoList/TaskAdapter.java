package com.youdo.TodoList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.youdo.R;

import java.util.ArrayList;

//Adapter for the listview. Works as a middle man to transfere data from objects onto the list.

class TaskAdapter extends ArrayAdapter<Task>{

    public TaskAdapter(@NonNull Context context, ArrayList<Task> resource) {
        super(context, R.layout.task_row,resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //Gets row layout to be used in listview.
        LayoutInflater taskInflater = LayoutInflater.from(getContext());
        View customView = taskInflater.inflate(R.layout.task_row,parent,false);

        //Gets and set the name of the task in the list.
        String name = getItem(position).getName();
        TextView taskName = (TextView) customView.findViewById(R.id.todoName);
        taskName.setText(name);

        return customView;
    }
}
