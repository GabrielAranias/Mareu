package com.gabriel.aranias.mareu.controller.fragments;

import static com.gabriel.aranias.mareu.controller.activities.MeetingsActivity.EXTRA_MEETING;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gabriel.aranias.mareu.R;
import com.gabriel.aranias.mareu.databinding.FragmentMeetingDetailsBinding;
import com.gabriel.aranias.mareu.model.Attendee;
import com.gabriel.aranias.mareu.model.Meeting;

public class MeetingDetailsFragment extends Fragment {

    private FragmentMeetingDetailsBinding binding;

    public MeetingDetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMeetingDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        displayMeetingDetails();
    }

    // Set up meeting details components to be displayed
    private void displayMeetingDetails() {
        Intent intent = requireActivity().getIntent();
        if (intent.getExtras() != null) {
            Meeting meeting = (Meeting) intent.getSerializableExtra(EXTRA_MEETING);
            binding.roomIcon.setImageResource(meeting.getRoom().getRoomIcon());
            binding.meetingInfo.setText(String.format("%s - %s - %s", meeting.getTopic(),
                    meeting.getTime(), meeting.getRoom().getRoomName()));
            getMeetingAttendees(meeting);
        }
    }

    // Display or hide all email addresses of meeting attendees when user clicks on btn
    private void getMeetingAttendees(Meeting meeting) {
        Button btn = binding.meetingAttendees;
        TextView tv = binding.meetingAttendeesDetails;
        tv.setVisibility(View.INVISIBLE);
        btn.setOnClickListener(v -> {
            if (tv.getVisibility() == View.VISIBLE) {
                tv.setVisibility(View.INVISIBLE);
                btn.setText(R.string.show);
            } else {
                tv.setVisibility(View.VISIBLE);
                btn.setText(R.string.hide);
                tv.setText(getStringBuilder(meeting));
            }
        });
    }

    // Display properly list of attendees
    private StringBuilder getStringBuilder(Meeting meeting) {
        StringBuilder sb = new StringBuilder();
        for (Attendee attendee : meeting.getAttendees()) {
            sb.append(attendee.getAttendee()).append("\n");
            sb.append(System.lineSeparator());
        }
        return sb;
    }
}
