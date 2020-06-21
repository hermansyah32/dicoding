package com.manheadblog.moviecatalogue.ui.tvshow;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.testing.SingleFragmentActivity;
import com.manheadblog.moviecatalogue.utils.EspressoIdlingResource;
import com.manheadblog.moviecatalogue.utils.RecyelerViewItemCountAssertion;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class TVShowDiscoverFragmentTest {

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private TVShowDiscoverFragment tvShowDiscoverFragment = new TVShowDiscoverFragment();

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
        activityRule.getActivity().setFragment(tvShowDiscoverFragment);
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadData() {
        onView(withId(R.id.recyclerViewTVShow)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerViewTVShow)).check(new RecyelerViewItemCountAssertion(20));
    }
}