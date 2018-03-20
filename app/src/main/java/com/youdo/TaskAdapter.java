package com.youdo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Lyle on 20/03/2018.
 */

class TaskAdapter extends ArrayAdapter<Task>{

    public TaskAdapter(@NonNull Context context, ArrayList<Task> resource) {
        super(context,R.layout.task_row,resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater taskInflater = LayoutInflater.from(getContext());
        View customView = taskInflater.inflate(R.layout.task_row,parent,false);

        String name = getItem(position).getName();
        TextView taskName = (TextView) customView.findViewById(R.id.todoName);

        taskName.setText(name);
        return customView;
    }
}
