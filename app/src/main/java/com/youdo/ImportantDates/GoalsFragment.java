package com.youdo.ImportantDates;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.youdo.DatabaseHelper;
import com.youdo.R;
import com.youdo.Schedule.AddToScheduleActivity;
import com.youdo.Schedule.ScheduleActivity;

import java.util.ArrayList;

public class GoalsFragment extends Fragment {

    private DatabaseHelper database;
    ListView goalList;
    ArrayAdapter<String> mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goals_fragment, container, false);

        goalList = (ListView) view.findViewById(R.id.goalsList); //Reference to goal list
        database = new DatabaseHelper(getContext()); //Reference to db

        //If the add goal button is clicked user is taken to add goal activity.
        FloatingActionButton addGoal = view.findViewById(R.id.buttonAddGoal);
        addGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addGoal = new Intent(getContext(), AddGoalActivity.class);
                startActivity(addGoal);
            }
        });

        //Populates list view with all goals.
        populateListView();

        return view;
    }

    //Populates list view with all goals.
    private void populateListView() {

        //Fetches all goals from database and stores in array.
        ArrayList<String> dbList = database.getGoalData();

        //Displays the goals in the aarray list on the list view using the adapter.
        if(mAdapter==null) {
            mAdapter = new GoalAdapter(getContext(),dbList);
            goalList.setAdapter(mAdapter);
        }else{
            mAdapter.clear();
            mAdapter.addAll(dbList);
            mAdapter.notifyDataSetChanged();
        }
    }

}
