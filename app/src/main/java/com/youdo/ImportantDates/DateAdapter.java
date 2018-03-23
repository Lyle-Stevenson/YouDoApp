package com.youdo.ImportantDates;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.youdo.Calendar.Event;
import com.youdo.R;

import java.util.ArrayList;

/**
 * Created by Lyle on 23/03/2018.
 */

public class DateAdapter extends ArrayAdapter<Date> {

    public DateAdapter(@NonNull Context context, ArrayList<Date> resource) {
        super(context, R.layout.date_row,resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater dateInflater = LayoutInflater.from(getContext());
        View customView = dateInflater.inflate(R.layout.date_row,parent,false);

        String name = getItem(position).getName();
        TextView dateName = (TextView) customView.findViewById(R.id.dateName);

        String date = getItem(position).getDate();
        TextView dateDate = (TextView) customView.findViewById(R.id.dateDate);

        dateName.setText(name);
        dateDate.setText(date);

        return customView;
    }
}
