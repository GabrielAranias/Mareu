package com.gabriel.aranias.mareu.controller.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabriel.aranias.mareu.databinding.FragmentMeetingDetailsBinding;
import com.gabriel.aranias.mareu.model.Meeting;

public class MeetingDetailsFragment extends Fragment {

    public MeetingDetailsFragment() {
    }

    private FragmentMeetingDetailsBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMeetingDetailsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        displayMeetingDetails();

        return view;
    }


    // Set up meeting details components to be displayed
    private void displayMeetingDetails() {
        String MEETING = "MEETING";
        if (requireActivity().getIntent().hasExtra(MEETING)) {
            Meeting meeting = requireActivity().getIntent().getParcelableExtra(MEETING);
            binding.roomIcon.setImageResource(meeting.getRoom().getRoomIcon());
            binding.meetingInfo.setText(String.format("%s - %s - %s", meeting.getTopic(),
                    meeting.getTime(), meeting.getRoom().getRoomName()));

            // TODO: 20/08/2021 meetingAttendees
        }
    }
}