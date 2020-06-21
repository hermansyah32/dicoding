package com.manheadblog.moviecatalogue.ui;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.manheadblog.moviecatalogue.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityTest {
    private String dummyTextMovie = "Aquaman";
    private String dummyTextTVShow = "Arrow";

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void performMovieDetail() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()));
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()));

        //Swipe Pager for changing tab
        onView(withId(R.id.view_pager)).perform(swipeLeft());
        onView(withId(R.id.view_pager)).perform(swipeRight());

        //Check movie detail activity
        onView(withId(R.id.recyclerViewMovie)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.textViewTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewTitle)).check(matches(withText(dummyTextMovie)));
    }

    @Test
    public void performTVShowDetail() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()));
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()));

        //Swipe Pager for changing tab
        onView(withId(R.id.view_pager)).perform(swipeLeft());

        //Check movie detail activity
        onView(withId(R.id.recyclerViewTVShow)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.textViewTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewTitle)).check(matches(withText(dummyTextTVShow)));


    }
}