package com.gabriel.aranias.mareu.controller.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gabriel.aranias.mareu.R;
import com.gabriel.aranias.mareu.controller.fragments.AddMeetingFragment;
import com.gabriel.aranias.mareu.databinding.ActivityAddMeetingBinding;

public class AddMeetingActivity extends AppCompatActivity {

    private ActivityAddMeetingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        configureToolbar();
        addFragment();
    }

    // Set toolbar w/ arrow back
    private void configureToolbar() {
        setSupportActionBar(binding.addMeetingToolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
    }

    // Configure and show AddMeetingFragment
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
