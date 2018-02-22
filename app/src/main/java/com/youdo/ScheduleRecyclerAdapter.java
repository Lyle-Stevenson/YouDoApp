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
 * Created by Lyle on 19/02/2018.
 */
//Adapter to assign schedule item data to the recycler view
public class ScheduleRecyclerAdapter extends RecyclerView.Adapter<com.youdo.ScheduleRecyclerAdapter.ScheduleViewHolder> {

        private ArrayList<ScheduleItem> listSchedule; // List of shcedule items to display
        private Context mContext; // context means what activity are we in

        //Constructor
        public ScheduleRecyclerAdapter(ArrayList<ScheduleItem> listSchedule, Context mContext) {
            this.listSchedule = listSchedule;
            this.mContext = mContext;
        }

        //This class gets the spaces on the wiew where we are to store the schedule items information
        public class ScheduleViewHolder extends RecyclerView.ViewHolder {

            TextView schedule_name;
            TextView schedule_end_time;
            TextView schedule_start_time;

            public ScheduleViewHolder(View view) {
                super(view);
                schedule_name = (TextView) itemView.findViewById(R.id.schedName);
                schedule_end_time = (TextView) itemView.findViewById(R.id.schedEndTime);
                schedule_start_time = (TextView) itemView.findViewById(R.id.schedStartTime);
            }


        }

        //This method I have no idea about
        @Override
        public com.youdo.ScheduleRecyclerAdapter.ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // inflating recycler item view
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_row,parent,false);

            return new com.youdo.ScheduleRecyclerAdapter.ScheduleViewHolder(itemView);
        }

        //This methods applys the data to the view
        @Override
        public void onBindViewHolder(final com.youdo.ScheduleRecyclerAdapter.ScheduleViewHolder holder, int position) {
            holder.schedule_name.setText(listSchedule.get(position).getName());
            holder.schedule_start_time.setText(listSchedule.get(position).getStart());
            holder.schedule_end_time.setText(listSchedule.get(position).getEnd());
        }

    @Override
    public int getItemCount() {
        return 0;
    }

}

