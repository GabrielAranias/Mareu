package com.gabriel.aranias.mareu.controller.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.gabriel.aranias.mareu.R;
import com.gabriel.aranias.mareu.controller.fragments.AddMeetingFragment;

public class AddMeetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        configureToolbar();
        addFragment();
    }

    // Set toolbar w/ arrow back
    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.add_meeting_toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
    }

    //Configure and show AddMeetingFragment
    private void addFragment() {
        AddMeetingFragment fragment = (AddMeetingFragment) getSupportFragmentManager()
                .findFragmentById(R.id.add_meeting_container);
        if (fragment == null) {
            fragment = new AddMeetingFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.add_meeting_container, fragment)
                    .commit();
        }
    }
}