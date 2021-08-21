package com.gabriel.aranias.mareu.service;

import com.gabriel.aranias.mareu.model.Meeting;
import com.gabriel.aranias.mareu.model.Room;

import java.time.LocalTime;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {
    private final List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void createMeeting(Meeting meeting) { meetings.add(meeting); }

    public Meeting getMeetingByPosition(int position) {
        return meetings.get(position);
    }

    public void deleteMeeting(Meeting meeting) { meetings.remove(meeting); }
}
