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

/**
 * Created by Lyle on 20/03/2018.
 */

class EventAdapter extends ArrayAdapter<Event>{

    public EventAdapter(@NonNull Context context, ArrayList<Event> resource) {
        super(context, R.layout.event_row,resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater eventInflater = LayoutInflater.from(getContext());
        View customView = eventInflater.inflate(R.layout.event_row,parent,false);

        String name = getItem(position).getName();
        TextView eventName = (TextView) customView.findViewById(R.id.eventName);

        String desc = getItem(position).getDescription();
        TextView eventDesc = (TextView) customView.findViewById(R.id.eventDescription);

        String time = getItem(position).getTime();
        TextView eventTime = (TextView) customView.findViewById(R.id.eventTime);

        eventName.setText(name);
        eventDesc.setText(desc);
        eventTime.setText(time);

        return customView;
    }
}
