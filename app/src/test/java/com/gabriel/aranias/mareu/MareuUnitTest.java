package com.gabriel.aranias.mareu;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gabriel.aranias.mareu.di.DI;
import com.gabriel.aranias.mareu.model.Attendee;
import com.gabriel.aranias.mareu.model.Meeting;
import com.gabriel.aranias.mareu.model.Room;
import com.gabriel.aranias.mareu.service.DummyMeetingGenerator;
import com.gabriel.aranias.mareu.service.MeetingApiService;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MareuUnitTest {

    private MeetingApiService service;
    private final Meeting testMeeting = new Meeting(new Room("fake_name", 42), "fake_topic",
            "fake_time", new List<Attendee>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(@Nullable Object o) {
            return false;
        }

        @NonNull
        @Override
        public Iterator<Attendee> iterator() {
            return iterator();
        }

        @NonNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @NonNull
        @Override
        public <T> T[] toArray(@NonNull T[] a) {
            return a;
        }

        @Override
        public boolean add(Attendee attendee) {
            return false;
        }

        @Override
        public boolean remove(@Nullable Object o) {
            return false;
        }

        @Override
        public boolean containsAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(@NonNull Collection<? extends Attendee> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, @NonNull Collection<? extends Attendee> c) {
            return false;
        }

        @Override
        public boolean removeAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public boolean equals(@Nullable Object o) {
            return false;
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public Attendee get(int index) {
            return null;
        }

        @Override
        public Attendee set(int index, Attendee element) {
            return null;
        }

        @Override
        public void add(int index, Attendee element) {

        }

        @Override
        public Attendee remove(int index) {
            return null;
        }

        @Override
        public int indexOf(@Nullable Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(@Nullable Object o) {
            return 0;
        }

        @NonNull
        @Override
        public ListIterator<Attendee> listIterator() {
            return listIterator();
        }

        @NonNull
        @Override
        public ListIterator<Attendee> listIterator(int index) {
            return listIterator();
        }

        @NonNull
        @Override
        public List<Attendee> subList(int fromIndex, int toIndex) {
            return subList(0, 0);
        }
    });

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getMeetingWithSuccess() {
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
    }
}