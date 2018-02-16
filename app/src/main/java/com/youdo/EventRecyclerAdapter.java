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
 * Created by Lyle on 16/02/2018.
 */

public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.CalendarViewHolder> {

    private ArrayList<Event> listEvents;
    private Context mContext;
    private ArrayList<Event> mFilteredList;


    public EventRecyclerAdapter(ArrayList<Event> listEvents, Context mContext) {
        this.listEvents = listEvents;
        this.mContext = mContext;
    }

    public class CalendarViewHolder extends RecyclerView.ViewHolder {

        TextView event_name;
        TextView event_des;
        TextView event_time;

        public CalendarViewHolder(View view) {
            super(view);
            event_name = (TextView) itemView.findViewById(R.id.eventName);
            event_des = (TextView) itemView.findViewById(R.id.eventDescription);
            event_time = (TextView) itemView.findViewById(R.id.eventTime);
        }


    }

    @Override
    public EventRecyclerAdapter.CalendarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_row,parent,false);

        return new EventRecyclerAdapter.CalendarViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final EventRecyclerAdapter.CalendarViewHolder holder, int position) {
        Log.d("List", listEvents.get(position).toString());
        holder.event_name.setText(listEvents.get(position).getName());
        holder.event_des.setText(listEvents.get(position).getDescription());
        holder.event_time.setText(listEvents.get(position).getTime());
    }


    @Override
    public int getItemCount() {
        return listEvents.size();
    }
}
