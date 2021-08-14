package com.gabriel.aranias.mareu.controller.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.gabriel.aranias.mareu.R;
import com.gabriel.aranias.mareu.controller.fragments.MeetingDetailsFragment;

public class MeetingDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_details);

        configureToolbar();
        addFragment();
    }

    // Set toolbar w/ arrow back
    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.meeting_details_toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
    }

    //Configure and show MeetingDetailsFragment
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