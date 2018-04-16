package com.youdo.Calendar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.youdo.R;

import java.util.ArrayList;

//Adapter for the listview. Works as a middle man to transfer data from objects onto the list.

class EventAdapter extends ArrayAdapter<Event>{

    public EventAdapter(@NonNull Context context, ArrayList<Event> resource) {
        super(context, R.layout.event_row,resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //Gets row layout to be used in listview.
        LayoutInflater eventInflater = LayoutInflater.from(getContext());
        View customView = eventInflater.inflate(R.layout.event_row,parent,false);

        //Adds the event variables to the view.
        String name = getItem(position).getName();
        TextView eventName = (TextView) customView.findViewById(R.id.eventName);

        String time = getItem(position).getTime();
        TextView eventTime = (TextView) customView.findViewById(R.id.eventTime);

        eventName.setText(name);
        eventTime.setText(time);

        return customView;
    }
}
