package com.youdo.ImportantDates;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
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
import com.youdo.Schedule.ScheduleItem;

import java.util.ArrayList;

/**
 * Created by Lyle on 23/03/2018.
 */

public class DatesFragment extends Fragment {

    private DatabaseHelper database;
    ListView dateList;
    ArrayAdapter<Date> mAdapter;

    private static final String TAG = "DatesFragment";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dates_fragment, container, false);

        dateList = (ListView) view.findViewById(R.id.datesList);

        database = new DatabaseHelper(getContext());

        FloatingActionButton addDate = view.findViewById(R.id.buttonAddDate);
        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addDate = new Intent(getContext(), AddDateActivity.class);
                startActivity(addDate);
            }
        });

        populateListView();

        return view;
    }

    private void populateListView() {

        ArrayList<Date> dbList = database.getDateData();
        if(mAdapter==null) {
            mAdapter = new DateAdapter(getContext(),dbList);
            dateList.setAdapter(mAdapter);
        }else{
            mAdapter.clear();
            mAdapter.addAll(dbList);
            mAdapter.notifyDataSetChanged();
        }
    }
}
