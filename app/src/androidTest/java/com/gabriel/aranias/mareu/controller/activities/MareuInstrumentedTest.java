package com.gabriel.aranias.mareu.controller.activities;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.gabriel.aranias.mareu.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MareuInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MeetingsActivity> rule = new ActivityScenarioRule<>(MeetingsActivity.class);

    @Test
    public void createMeetingWithSuccess() {

        // Check that RecyclerView is empty
        onView(ViewMatchers.withId(R.id.meetings_list)).check(matches(hasChildCount(0)));
        // Click on fab to add new meeting
        onView(allOf(withId(R.id.add_meeting_fab), isDisplayed())).perform(click());
        // Fill in topic field
        onView(allOf(withId(R.id.topic_et), isDisplayed())).perform(replaceText("test"));
        // Fill in attendee field
        onView(allOf(withId(R.id.attendees_et), isDisplayed())).perform(replaceText("test@lamzone.com"));
        // Add attendee
        onView(allOf(withId(R.id.add_attendee_btn), isDisplayed())).perform(click());
        // Pick meeting time
        onView(allOf(withId(R.id.time_picker), isDisplayed())).perform(click());
        // Validate picked time
        onView(allOf(withId(android.R.id.button1), withText("OK"), isDisplayed())).perform(click());
        // Click on Spinner to pick meeting room
        onView(allOf(withId(R.id.room_spinner), isDisplayed())).perform(click());
        // Pick meeting room
        onData(anything()).inAdapterView(childAtPosition(withClassName(is("android.widget.PopupWindow$PopupBackgroundView"))
        )).atPosition(5).perform(click());
        // Click on fab to create meeting
        onView(allOf(withId(R.id.check_add_meeting_fab), isDisplayed())).perform(click());
        // Check that RecyclerView contains new meeting
        onView(ViewMatchers.withId(R.id.meetings_list)).check(matches(hasChildCount(1)));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        // Add new meeting
        createMeetingWithSuccess();
        // Click on delete btn
        onView(allOf(withId(R.id.delete_btn), isDisplayed())).perform(click());
        // Check that RecyclerView is empty
        onView(ViewMatchers.withId(R.id.meetings_list)).check(matches(hasChildCount(0)));
    }

    @Test
    public void displayMeetingDetailsWithSuccess() {
        // Add new meeting
        createMeetingWithSuccess();
        // Click on meeting item in RecyclerView
        onView(allOf(withId(R.id.meeting_info_item), isDisplayed())).perform(click());
        // Check that details are displayed
        onView(allOf(withId(R.id.meeting_details_view), isDisplayed())).check(matches(isDisplayed()));
        // Display list of meeting attendees
        onView(allOf(withId(R.id.meeting_attendees), withText("VOIR LES PARTICIPANTS"), isDisplayed())).perform(click());
        // Check that list is displayed
        onView(allOf(withId(R.id.meeting_attendees_details), isDisplayed())).check(matches(isDisplayed()));
        // Hide list of meetings attendees
        onView(allOf(withId(R.id.meeting_attendees), withText("MASQUER LES PARTICIPANTS"), isDisplayed())).perform(click());
        // Check that list is hidden
        onView(withId(R.id.meeting_attendees_details)).check(matches(not(isDisplayed())));
        // Go back to meeting list
        pressBack();
        // Delete meeting
        onView(allOf(withId(R.id.delete_btn), isDisplayed())).perform(click());
    }

    @Test
    public void filterMeetingsByRoomWithSuccess() {
        // Add new meeting in the 'Mario' room
        createMeetingWithSuccess();
        // Click on room filter
        onView(allOf(withId(R.id.meetings_menu_room_filter), isDisplayed())).perform(click());
        // Search for 'Mario' meeting room
        onView(allOf(withId(R.id.search_src_text), isDisplayed())).perform(replaceText("mario"));
        // Check that RecyclerView contains meeting
        onView(ViewMatchers.withId(R.id.meetings_list)).check(matches(hasChildCount(1)));
        // Go back to meeting list
        pressBack();
        // Search for 'Luigi' meeting room
        onView(allOf(withId(R.id.search_src_text), isDisplayed())).perform(replaceText("luigi"));
        // Check that RecyclerView doesn't contain meeting
        onView(ViewMatchers.withId(R.id.meetings_list)).check(matches(hasChildCount(0)));
        // Go back to meeting list
        pressBack();
        // Delete meeting
        onView(allOf(withId(R.id.delete_btn), isDisplayed())).perform(click());
    }

    @Test
    public void filterMeetingsByTimeWithSuccess() {
        // Add new meeting starting at 12 am
        createMeetingWithSuccess();
        // Click on time filter
        onView(allOf(withId(R.id.meetings_menu_time_filter), isDisplayed())).perform(click());
        // Search for meetings starting at 12 am
        onView(allOf(withId(R.id.search_src_text), isDisplayed())).perform(replaceText("00"));
        // Check that RecyclerView contains meeting
        onView(ViewMatchers.withId(R.id.meetings_list)).check(matches(hasChildCount(1)));
        // Go back to meeting list
        pressBack();
        // Search for meetings starting at 5 pm
        onView(allOf(withId(R.id.search_src_text), isDisplayed())).perform(replaceText("17"));
        // Check that RecyclerView doesn't contain meeting
        onView(ViewMatchers.withId(R.id.meetings_list)).check(matches(hasChildCount(0)));
        // Go back to meeting list
        pressBack();
        // Delete meeting
        onView(allOf(withId(R.id.delete_btn), isDisplayed())).perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + 0 + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(0));
            }
        };
    }
}
