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

/**
 * Created by Lyle on 23/03/2018.
 */

public class GoalsFragment extends Fragment {

    private DatabaseHelper database;
    ListView goalList;
    ArrayAdapter<String> mAdapter;

    private static final String TAG = "GoalsFragment";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goals_fragment, container, false);

        goalList = (ListView) view.findViewById(R.id.goalsList);
        database = new DatabaseHelper(getContext());

        FloatingActionButton addGoal = view.findViewById(R.id.buttonAddGoal);
        addGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addGoal = new Intent(getContext(), AddGoalActivity.class);
                startActivity(addGoal);
            }
        });

        populateListView();
        return view;
    }

    private void populateListView() {

        ArrayList<String> dbList = database.getGoalData();
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
