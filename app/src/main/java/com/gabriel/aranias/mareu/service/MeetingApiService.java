package com.gabriel.aranias.mareu.service;

import com.gabriel.aranias.mareu.model.Meeting;
import com.gabriel.aranias.mareu.model.Room;

import java.time.LocalTime;
import java.util.List;

public interface MeetingApiService {
    List<Meeting> getMeetings();

    void createMeeting(Meeting meeting);

    Meeting getMeetingByPosition(int position);

    void deleteMeeting(Meeting meeting);
}
