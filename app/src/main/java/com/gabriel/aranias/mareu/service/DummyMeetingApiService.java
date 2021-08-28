package com.gabriel.aranias.mareu.service;

import com.gabriel.aranias.mareu.model.Meeting;

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
}
