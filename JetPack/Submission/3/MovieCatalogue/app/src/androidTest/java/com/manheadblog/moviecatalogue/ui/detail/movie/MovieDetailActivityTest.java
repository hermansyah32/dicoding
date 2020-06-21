package com.manheadblog.moviecatalogue.ui.detail.movie;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.entity.Movie;
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
public class MovieDetailActivityTest {
    private Movie dummyData = FakeDataDummy.generateMovies(getClass().getClassLoader()).get(0);

    @Rule
    public ActivityTestRule<MovieDetailActivity> activityRule = new ActivityTestRule<MovieDetailActivity>(MovieDetailActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent intent = new Intent(targetContext, MovieDetailActivity.class);
            intent.putExtra(MovieDetailActivity.DATA, dummyData);
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
    public void loadData(){
        onView(withId(R.id.imageViewBackgroundMovieDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.imageViewPosterMovieDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewTitleMovieDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewOverviewMovieDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewLanguageMovieDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewReleaseMovieDetail)).check(matches(isDisplayed()));

        onView(withId(R.id.textViewTitleMovieDetail)).check(matches(withText(dummyData.getTitle())));
    }
}