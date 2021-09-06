package com.gabriel.aranias.mareu.model;

import java.io.Serializable;

public class Attendee implements Serializable {

    private final String attendee;

    public Attendee(String attendee) {
        this.attendee = attendee;
    }

    public String getAttendee() {
        return attendee;
    }
}
