package com.youdo.Schedule;

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

class ScheduleAdapter extends ArrayAdapter<ScheduleItem>{

    public ScheduleAdapter(@NonNull Context context, ArrayList<ScheduleItem> resource) {
        super(context, R.layout.schedule_row,resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater scheduleInflater = LayoutInflater.from(getContext());
        View customView = scheduleInflater.inflate(R.layout.schedule_row,parent,false);

        String name = getItem(position).getName();
        TextView schedName = (TextView) customView.findViewById(R.id.schedName);

        String start = getItem(position).getStart();
        TextView schedStart = (TextView) customView.findViewById(R.id.schedStartTime);

        String end = getItem(position).getEnd();
        TextView schedEnd = (TextView) customView.findViewById(R.id.schedEndTime);

        schedName.setText(name);
        schedStart.setText(start);
        schedEnd.setText(end);

        return customView;
    }
}
