package com.manheadblog.moviecatalogue.ui.movie;

import androidx.test.rule.ActivityTestRule;

import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.testing.SingleFragmentActivity;
import com.manheadblog.moviecatalogue.utils.RecyelerViewItemCountAssertion;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class MovieDiscoverFragmentTest {

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private MovieDiscoverFragment tvShowDiscoverFragment = new MovieDiscoverFragment();

    @Before
    public void setUp() {
        activityRule.getActivity().setFragment(tvShowDiscoverFragment);
    }

    @Test
    public void loadData() {
        onView(withId(R.id.recyclerViewMovie)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerViewMovie)).check(new RecyelerViewItemCountAssertion(12));
    }
}