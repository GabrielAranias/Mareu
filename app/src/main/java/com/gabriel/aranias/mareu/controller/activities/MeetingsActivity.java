package com.gabriel.aranias.mareu.controller.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

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

    private ActivityMeetingsBinding binding;
    private MeetingRecyclerViewAdapter adapter;
    private MeetingApiService service;
    public static final String EXTRA_MEETING = "meeting";

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
    @SuppressLint("NotifyDataSetChanged")
    private void initMeetingList() {
        List<Meeting> meetings = new ArrayList<>(service.getMeetings());
        adapter.updateMeetingList(meetings, this);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_meetings, menu);

        setRoomFilter(menu);
        setTimeFilter(menu);

        return true;
    }

    // Set room filter in toolbar
    private void setRoomFilter(Menu menu) {
        MenuItem roomFilter = menu.findItem(R.id.meetings_menu_room_filter);
        SearchView sv = (SearchView) roomFilter.getActionView();
        sv.setImeOptions(EditorInfo.IME_ACTION_DONE);
        sv.setQueryHint(getString(R.string.room_search_txt));
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    // Set time filter in toolbar
    private void setTimeFilter(Menu menu) {
        MenuItem timeFilter = menu.findItem(R.id.meetings_menu_time_filter);
        SearchView sv = (SearchView) timeFilter.getActionView();
        sv.setImeOptions(EditorInfo.IME_ACTION_DONE);
        sv.setQueryHint(getString(R.string.time_search_txt));
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getTimeFilter().filter(newText);
                return false;
            }
        });
    }

    // Set customized toolbar
    private void configureToolbar() {
        setSupportActionBar(binding.meetingsToolbar);
    }

    // Start activity to create new meeting when user clicks on fab
    private void addMeeting() {
        binding.addMeetingFab.setOnClickListener(v -> {
            Intent addMeetingIntent = new Intent(MeetingsActivity.this,
                    AddMeetingActivity.class);
            startActivity(addMeetingIntent);
        });
    }

    // Display meeting details when user clicks on item
    @Override
    public void onMeetingClicked(Meeting meeting) {
        Intent meetingDetailsIntent = new Intent(MeetingsActivity.this,
                MeetingDetailsActivity.class);

        meetingDetailsIntent.putExtra(EXTRA_MEETING, meeting);
        startActivity(meetingDetailsIntent);
    }

    //  Remove meeting from the list when user clicks on btn
    @Override
    public void onDeleteBtnClicked(Meeting meeting) {
        service.deleteMeeting(meeting);
        initMeetingList();
        Toast.makeText(this, R.string.deleted, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initMeetingList();
    }
}