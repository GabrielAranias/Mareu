package com.gabriel.aranias.mareu.di;

import com.gabriel.aranias.mareu.service.DummyMeetingApiService;
import com.gabriel.aranias.mareu.service.MeetingApiService;

public class DI {

    private static final MeetingApiService service = new DummyMeetingApiService();

    public static MeetingApiService getMeetingApiService() {
        return service;
    }

    public static MeetingApiService getNewInstanceApiService() {
        return new DummyMeetingApiService();
    }
}
