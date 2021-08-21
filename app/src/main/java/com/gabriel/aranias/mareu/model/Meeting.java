package com.gabriel.aranias.mareu.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Meeting implements Parcelable {

    private Room room;
    private final String topic;
    private final String time;
    private final String attendees;

    public Meeting(Room room, String topic, String time, String attendees) {
        this.room = room;
        this.topic = topic;
        this.time = time;
        this.attendees = attendees;
    }

    protected Meeting(Parcel in) {
        topic = in.readString();
        time = in.readString();
        attendees = in.readString();
    }

    public static final Creator<Meeting> CREATOR = new Creator<Meeting>() {
        @Override
        public Meeting createFromParcel(Parcel in) {
            return new Meeting(in);
        }

        @Override
        public Meeting[] newArray(int size) {
            return new Meeting[size];
        }
    };

    public Room getRoom() {
        return room;
    }
    public String getTopic() {
        return topic;
    }
    public String getTime() {
        return time;
    }
    public String getAttendees() {
        return attendees;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(topic);
        dest.writeString(time);
        dest.writeString(attendees);
    }
}
