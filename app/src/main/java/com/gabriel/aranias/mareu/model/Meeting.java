package com.gabriel.aranias.mareu.model;

import java.io.Serializable;
import java.util.List;

public class Meeting implements Serializable {

    private final Room room;
    private final String topic;
    private final String time;
    private final List<Attendee> attendees;

    public Meeting(Room room, String topic, String time, List<Attendee> attendees) {
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

    public String getTime() {
        return time;
    }

    public List<Attendee> getAttendees() {
        return attendees;
    }
}