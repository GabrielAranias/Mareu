package com.gabriel.aranias.mareu.service;

import com.gabriel.aranias.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {
    private final List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();

    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    @Override
    public List<Meeting> filterMeetingsByRoom(CharSequence constraint) {
        List<Meeting> filteredList = new ArrayList<>();
        if (constraint == null || constraint.length() == 0) {
            filteredList.addAll(meetings);
        } else {
            String filterPattern = constraint.toString().toLowerCase().trim();
            for (Meeting filteredMeeting : meetings) {
                if (filteredMeeting.getRoom().getRoomName().toLowerCase().contains(filterPattern)) {
                    filteredList.add(filteredMeeting);
                }
            }
        }
        return filteredList;
    }

    @Override
    public List<Meeting> filterMeetingsByTime(CharSequence constraint) {
        List<Meeting> filteredList = new ArrayList<>();
        if (constraint == null || constraint.length() == 0) {
            filteredList.addAll(meetings);
        } else {
            String filterPattern = constraint.toString().toLowerCase().trim();
            for (Meeting filteredMeeting : meetings) {
                if (filteredMeeting.getTime().toLowerCase().startsWith(filterPattern)) {
                    filteredList.add(filteredMeeting);
                }
            }
        }
        return filteredList;
    }
}