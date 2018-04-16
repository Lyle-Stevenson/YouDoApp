package com.youdo.ImportantDates;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.youdo.R;
import com.youdo.Schedule.ScheduleItem;

import java.util.ArrayList;

//Adapter for the listview. Works as a middle man to transfer data from objects onto the list.

public class GoalAdapter extends ArrayAdapter<String> {

    public GoalAdapter(@NonNull Context context, ArrayList<String> resource) {
        super(context, R.layout.goal_row,resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //Gets row layout to be used in listview.
        LayoutInflater goalInflater = LayoutInflater.from(getContext());
        View customView = goalInflater.inflate(R.layout.goal_row,parent,false);

        //Adds the goal variables to the view.
        String name = getItem(position).toString();
        TextView goalName = (TextView) customView.findViewById(R.id.GoalName);

        goalName.setText(name);
        return customView;
    }
}
