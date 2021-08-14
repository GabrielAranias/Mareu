package com.gabriel.aranias.mareu.controller.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.gabriel.aranias.mareu.R;
import com.gabriel.aranias.mareu.controller.adapter.MeetingRecyclerViewAdapter;
import com.gabriel.aranias.mareu.model.Meeting;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MeetingsActivity extends AppCompatActivity {

    private final List<Meeting> meetings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetings);

        configureToolbar();
        initMeetingList();
        addMeeting();
    }

    // Set customized toolbar
    // TODO: 10/08/2021 onOptionsItemSelected
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_meetings, menu);
        return true;
    }

    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.meetings_toolbar);
        setSupportActionBar(toolbar);
    }

    // Init meeting list via MeetingRecyclerViewAdapter
    private void initMeetingList() {
        MeetingRecyclerViewAdapter adapter = new MeetingRecyclerViewAdapter(meetings);
        RecyclerView rv = findViewById(R.id.meetings_list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

    }

    // Start activity to create new meeting when user clicks on fab
    private void addMeeting() {
        FloatingActionButton fab = findViewById(R.id.add_meeting_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent addMeetingActivity = new Intent(MeetingsActivity.this,
                            AddMeetingActivity.class);
                    startActivity(addMeetingActivity);
            }
        });
    }
}