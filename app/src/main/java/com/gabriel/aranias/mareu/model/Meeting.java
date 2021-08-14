package com.gabriel.aranias.mareu.model;

import java.time.LocalTime;
import java.util.List;

public class Meeting {

    private final Room room;
    private final String topic;
    private final LocalTime time;
    private final List<String> attendees;

    public Meeting(Room room, String topic, LocalTime time, List<String> attendees) {
        this.room = room;
        this.topic = topic;
        this.time = time;
        this.attendees = attendees;
    }

    public Room getRoom() {
        return room;
    }
    public String getTopic() {
        return topic;
    }
    public LocalTime getTime() {
        return time;
    }
    public List<String> getAttendees() {
        return attendees;
    }
}
