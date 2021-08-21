package com.gabriel.aranias.mareu.controller.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.gabriel.aranias.mareu.R;
import com.gabriel.aranias.mareu.controller.adapter.MeetingRecyclerViewAdapter;
import com.gabriel.aranias.mareu.controller.adapter.onMeetingClickListener;
import com.gabriel.aranias.mareu.databinding.ActivityMeetingsBinding;
import com.gabriel.aranias.mareu.di.DI;
import com.gabriel.aranias.mareu.model.Meeting;
import com.gabriel.aranias.mareu.service.MeetingApiService;

import java.util.ArrayList;
import java.util.List;

public class MeetingsActivity extends AppCompatActivity implements onMeetingClickListener {

    public static ActivityMeetingsBinding binding;
    private MeetingRecyclerViewAdapter adapter;
    private List<Meeting> meetings;
    private MeetingApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = DI.getMeetingApiService();
        binding = ActivityMeetingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adapter = new MeetingRecyclerViewAdapter();
        binding.meetingsList.setAdapter(adapter);

        initMeetingList();
        configureToolbar();
        addMeeting();
    }

    // Init list of meetings
    private void initMeetingList() {
        meetings = new ArrayList<>();
        meetings.addAll(service.getMeetings());
        adapter.updateMeetingList(meetings, this);
    }

    // Set customized toolbar
    // TODO: 10/08/2021 onOptionsItemSelected
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_meetings, menu);
        return true;
    }

    private void configureToolbar() {
        setSupportActionBar(binding.meetingsToolbar);
    }

    // Start activity to create new meeting when user clicks on fab
    private void addMeeting() {
        binding.addMeetingFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent addMeetingIntent = new Intent(getApplicationContext(),
                            AddMeetingActivity.class);
                    startActivity(addMeetingIntent);
            }
        });
    }

    @Override
    public void onMeetingClicked(int position) {
        Intent meetingDetailsIntent = new Intent(getApplicationContext(),
                MeetingDetailsActivity.class);

        meetingDetailsIntent.putExtra("meeting", meetings.get(position));
        startActivity(meetingDetailsIntent);
    }

    @Override
    public void onDeleteBtnClicked(int position) {
        meetings.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyDataSetChanged();
    }
}