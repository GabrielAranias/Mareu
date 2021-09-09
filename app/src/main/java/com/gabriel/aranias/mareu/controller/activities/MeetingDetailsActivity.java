package com.gabriel.aranias.mareu.controller.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gabriel.aranias.mareu.R;
import com.gabriel.aranias.mareu.controller.fragments.MeetingDetailsFragment;
import com.gabriel.aranias.mareu.databinding.ActivityMeetingDetailsBinding;

public class MeetingDetailsActivity extends AppCompatActivity {

    private ActivityMeetingDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMeetingDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        configureToolbar();
        addFragment();
    }

    // Set toolbar w/ arrow back
    private void configureToolbar() {
        setSupportActionBar(binding.meetingDetailsToolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
    }

    // Configure and show MeetingDetailsFragment
    private void addFragment() {
        MeetingDetailsFragment fragment = (MeetingDetailsFragment) getSupportFragmentManager()
                .findFragmentById(R.id.meeting_details_container);
        if (fragment == null) {
            fragment = new MeetingDetailsFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.meeting_details_container, fragment)
                    .commit();
        }
    }
}
