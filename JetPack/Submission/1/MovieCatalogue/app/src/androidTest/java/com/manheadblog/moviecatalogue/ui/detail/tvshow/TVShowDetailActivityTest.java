package com.manheadblog.moviecatalogue.ui.detail.tvshow;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.entity.TVShow;
import com.manheadblog.moviecatalogue.utils.FakeDataDummy;

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
    private TVShow dummyData = FakeDataDummy.generateTVShows().get(0);

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

    @Test
    public void loadData(){
        onView(withId(R.id.imageViewPoster)).check(matches(isDisplayed()));
        onView(withId(R.id.imageViewBackground)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewOverview)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewLanguage)).check(matches(isDisplayed()));

        onView(withId(R.id.textViewTitle)).check(matches(withText(dummyData.getTitle())));
        onView(withId(R.id.textViewOverview)).check(matches(withText(dummyData.getOverview())));
        onView(withId(R.id.textViewLanguage)).check(matches(withText(dummyData.getLanguage())));
    }

}