package com.manheadblog.moviecatalogue.useCase.favorite;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.adapter.SectionsPagerAdapter;
import com.manheadblog.moviecatalogue.useCase.main.MainActivity;

import java.util.ArrayList;
import java.util.Objects;

public class FavoriteActivity extends AppCompatActivity {

    private static final String TAG = FavoriteActivity.class.getSimpleName();
    public static final String REMOVED_MOVIE = "REMOVED_MOVIE";
    public static final String REMOVED_TVSHOW = "REMOVED_TVSHOW";

    public ArrayList<Integer> removedPositionMovie;
    public ArrayList<Integer> removedPositionTVShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        removedPositionMovie = new ArrayList<>();
        removedPositionTVShow = new ArrayList<>();

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentMovie());
        fragments.add(new FragmentTVShow());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), fragments);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent();
            intent.putIntegerArrayListExtra(REMOVED_MOVIE, removedPositionMovie);
            intent.putIntegerArrayListExtra(REMOVED_TVSHOW, removedPositionTVShow);

            setResult(MainActivity.RESULT_OK, intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putIntegerArrayListExtra(REMOVED_MOVIE, removedPositionMovie);
        intent.putIntegerArrayListExtra(REMOVED_TVSHOW, removedPositionTVShow);

        setResult(MainActivity.RESULT_OK, intent);
        super.onBackPressed();
    }

    public void addRemovedMovie(int id){
        this.removedPositionMovie.add(id);
    }

    public void addRemovedTVShow(int id){
        this.removedPositionTVShow.add(id);
    }

}