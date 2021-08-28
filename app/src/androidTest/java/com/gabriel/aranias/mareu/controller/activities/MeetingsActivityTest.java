package com.gabriel.aranias.mareu.controller.activities;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
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
public class MeetingsActivityTest {

    @Rule
    public ActivityScenarioRule<MeetingsActivity> mActivityScenarioRule = new ActivityScenarioRule<>(MeetingsActivity.class);

    @Test
    public void meetingsActivityTest() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.add_meeting_fab), withContentDescription("Fab to create a new meeting"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.topic_et),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.topic_til),
                                        0),
                                0)));
        textInputEditText.perform(scrollTo(), replaceText("Test instru"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.topic_et), withText("Test instru"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.topic_til),
                                        0),
                                0)));
        textInputEditText2.perform(pressImeActionButton());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.attendees_et),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.attendees_til),
                                        0),
                                0)));
        textInputEditText3.perform(scrollTo(), replaceText("henri.des@lamzone.com herve.renard@lamzone.com"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.add_attendee_btn), withText("Ajouter"),
                        childAtPosition(
                                allOf(withId(R.id.attendees_til),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                1)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.attendees_et),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.attendees_til),
                                        0),
                                0)));
        textInputEditText4.perform(pressImeActionButton());

        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.time_picker),
                        childAtPosition(
                                allOf(withId(R.id.time_layout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                3)),
                                0)));
        materialTextView.perform(scrollTo(), click());

        ViewInteraction materialButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.room_spinner), withContentDescription("Spinner to choose a meeting room"),
                        childAtPosition(
                                allOf(withId(R.id.room_layout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                4)),
                                0)));
        appCompatSpinner.perform(scrollTo(), click());

        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(5);
        relativeLayout.perform(click());

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.check_add_meeting_fab), withContentDescription("Fab to create a new meeting"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.add_meeting_container),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton2.perform(click());

        ViewInteraction materialTextView2 = onView(
                allOf(withId(R.id.meeting_info_item), withText("Test instru - 11:00 - Mario"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.cardview.widget.CardView")),
                                        0),
                                1)));
        materialTextView2.perform(scrollTo(), click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.meeting_attendees), withText("Voir les participants"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.meeting_attendees), withText("MASQUER LES PARTICIPANTS"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.meeting_details_toolbar),
                                        childAtPosition(
                                                withClassName(is("com.google.android.material.appbar.AppBarLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction floatingActionButton3 = onView(
                allOf(withId(R.id.add_meeting_fab), withContentDescription("Fab to create a new meeting"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        floatingActionButton3.perform(click());

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.topic_et),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.topic_til),
                                        0),
                                0)));
        textInputEditText5.perform(scrollTo(), replaceText("Test instru2"), closeSoftKeyboard());

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.topic_et), withText("Test instru2"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.topic_til),
                                        0),
                                0)));
        textInputEditText6.perform(pressImeActionButton());

        ViewInteraction textInputEditText7 = onView(
                allOf(withId(R.id.attendees_et),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.attendees_til),
                                        0),
                                0)));
        textInputEditText7.perform(scrollTo(), replaceText("alphonse.brown@lamzone.com"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.add_attendee_btn), withText("Ajouter"),
                        childAtPosition(
                                allOf(withId(R.id.attendees_til),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                1)));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction textInputEditText8 = onView(
                allOf(withId(R.id.attendees_et),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.attendees_til),
                                        0),
                                0)));
        textInputEditText8.perform(pressImeActionButton());

        ViewInteraction materialTextView3 = onView(
                allOf(withId(R.id.time_picker),
                        childAtPosition(
                                allOf(withId(R.id.time_layout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                3)),
                                0)));
        materialTextView3.perform(scrollTo(), click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.room_spinner), withContentDescription("Spinner to choose a meeting room"),
                        childAtPosition(
                                allOf(withId(R.id.room_layout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                4)),
                                0)));
        appCompatSpinner2.perform(scrollTo(), click());

        DataInteraction relativeLayout2 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(4);
        relativeLayout2.perform(click());

        ViewInteraction floatingActionButton4 = onView(
                allOf(withId(R.id.check_add_meeting_fab), withContentDescription("Fab to create a new meeting"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.add_meeting_container),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton4.perform(click());

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.meetings_menu_room_filter), withContentDescription("Filter meetings by room"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.meetings_toolbar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withId(R.id.search_src_text),
                        childAtPosition(
                                allOf(withId(R.id.search_plate),
                                        childAtPosition(
                                                withId(R.id.search_edit_frame),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("mario"), closeSoftKeyboard());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Collapse"),
                        childAtPosition(
                                allOf(withId(R.id.meetings_toolbar),
                                        childAtPosition(
                                                withClassName(is("com.google.android.material.appbar.AppBarLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.meetings_menu_time_filter), withContentDescription("Filter meetings by hour"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.meetings_toolbar),
                                        0),
                                1),
                        isDisplayed()));
        actionMenuItemView2.perform(click());

        ViewInteraction searchAutoComplete2 = onView(
                allOf(withId(R.id.search_src_text),
                        childAtPosition(
                                allOf(withId(R.id.search_plate),
                                        childAtPosition(
                                                withId(R.id.search_edit_frame),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete2.perform(replaceText("11"), closeSoftKeyboard());

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withContentDescription("Collapse"),
                        childAtPosition(
                                allOf(withId(R.id.meetings_toolbar),
                                        childAtPosition(
                                                withClassName(is("com.google.android.material.appbar.AppBarLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

        ViewInteraction appCompatImageButton4 = onView(
                allOf(withId(R.id.delete_btn), withContentDescription("Btn to delete a meeting"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.cardview.widget.CardView")),
                                        0),
                                3)));
        appCompatImageButton4.perform(scrollTo(), click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
