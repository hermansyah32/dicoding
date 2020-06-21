package com.manheadblog.moviecatalogue.useCase.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.adapter.SectionsPagerAdapter;
import com.manheadblog.moviecatalogue.service.AlarmReminder;
import com.manheadblog.moviecatalogue.useCase.config.SettingsActivity;
import com.manheadblog.moviecatalogue.useCase.favorite.FavoriteActivity;
import com.manheadblog.moviecatalogue.useCase.search.SearchActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final int MainActivityResult = 378;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new MoviesFragment());
        fragments.add(new TVShowsFragment());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), fragments);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        //Jalankan alarm untuk pertama kalinya
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences.getBoolean(getResources().getString(R.string.first_run_key), true)){
            if (preferences.getBoolean(getResources().getString(R.string.daily_notification_key),true)){
                AlarmReminder alarmReminder = new AlarmReminder();
                alarmReminder.setAlarm(MainActivity.this, "07:00", getResources().getString(R.string.daily_message), AlarmReminder.DAILY_REMINDER,AlarmReminder.DAILY_ID);
            }
            if (preferences.getBoolean(getResources().getString(R.string.newest_notification_key),true)){
                AlarmReminder alarmReminder = new AlarmReminder();
                alarmReminder.setAlarm(MainActivity.this, "08:00", getResources().getString(R.string.newest_message), AlarmReminder.RELEASE_REMINDER,AlarmReminder.RELEASE_ID);
            }
            preferences.edit().putBoolean(getResources().getString(R.string.first_run_key), false).apply();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_setting) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.action_favorite){
            Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
            startActivityForResult(intent, MainActivity.MainActivityResult);
        }
        if (item.getItemId() == R.id.action_search){
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}