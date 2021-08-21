package com.gabriel.aranias.mareu.model;

public class Room {
    private final String roomName;
    private final int roomIcon;

    public Room(String name, int icon) {
        roomName = name;
        roomIcon = icon;
    }

    public String getRoomName() {
        return roomName;
    }
    public int getRoomIcon() {
        return roomIcon;
    }
}
