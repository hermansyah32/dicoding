package com.manheadblog.moviecatalogue.ui.detail.tvshow;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.entity.TVShow;
import com.manheadblog.moviecatalogue.utils.EspressoIdlingResource;
import com.manheadblog.moviecatalogue.utils.FakeDataDummy;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class TVShowDetailActivityTest {
    private TVShow dummyData = FakeDataDummy.generateTVShows(getClass().getClassLoader()).get(0);

    @Rule
    public ActivityTestRule<TVShowDetailActivity> activityRule = new ActivityTestRule<TVShowDetailActivity>(TVShowDetailActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent intent = new Intent(targetContext, TVShowDetailActivity.class);
            intent.putExtra(TVShowDetailActivity.DATA, dummyData);
            return intent;
        }
    };

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadData() {
        onView(withId(R.id.imageViewBackgroundTVShowDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.imageViewPosterTVShowDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewTitleTVShowDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewOverviewTVShowDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewLanguageTVShowDetail)).check(matches(isDisplayed()));

        onView(withId(R.id.textViewOverviewTVShowDetail)).check(matches(withText(dummyData.getOverview())));
    }

}