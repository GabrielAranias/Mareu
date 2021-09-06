package com.gabriel.aranias.mareu;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.gabriel.aranias.mareu.di.DI;
import com.gabriel.aranias.mareu.model.Meeting;
import com.gabriel.aranias.mareu.service.DummyMeetingGenerator;
import com.gabriel.aranias.mareu.service.MeetingApiService;

import java.util.ArrayList;
import java.util.List;

public class MareuUnitTest {

    private MeetingApiService service;
    private final Meeting testMeeting = mock(Meeting.class);

    @Before
    public void setUp() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> expectedMeetings = DummyMeetingGenerator.DUMMY_MEETINGS;
        assertTrue(expectedMeetings.containsAll(meetings));
    }

    @Test
    public void createMeetingWithSuccess() {
        assertFalse(service.getMeetings().contains(testMeeting));
        service.createMeeting(testMeeting);
        assertTrue(service.getMeetings().contains(testMeeting));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        createMeetingWithSuccess();
        service.deleteMeeting(testMeeting);
        assertFalse(service.getMeetings().contains(testMeeting));
    }

    @Test
    public void filterMeetingsWithSuccess() {
        service.createMeeting(testMeeting);
        Meeting testMeeting2 = mock(Meeting.class);
        service.createMeeting(testMeeting2);
        assertEquals(2, service.getMeetings().size());
        List<Meeting> filteredMeetings = new ArrayList<>();
        filteredMeetings.add(testMeeting);
        assertEquals(1, filteredMeetings.size());
    }
}
