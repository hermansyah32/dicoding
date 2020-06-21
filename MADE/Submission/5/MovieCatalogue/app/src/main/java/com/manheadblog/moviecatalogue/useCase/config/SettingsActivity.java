package com.manheadblog.moviecatalogue.useCase.config;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.service.AlarmReminder;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener  {

        private String daily_key;
        private String newest_key;
        private String language_key;

        private SwitchPreferenceCompat daily_notification;
        private SwitchPreferenceCompat newest_notification;
        private Preference language;

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            init();
            setValue();
        }

        private void init(){
            daily_key = getResources().getString(R.string.daily_notification_key);
            newest_key = getResources().getString(R.string.newest_notification_key);
            language_key = getResources().getString(R.string.language_key);

            daily_notification = (SwitchPreferenceCompat) findPreference(daily_key);
            newest_notification = (SwitchPreferenceCompat) findPreference(newest_key);
            language = (Preference) findPreference(language_key);
            language.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                    startActivity(intent);
                    return false;
                }
            });
        }

        private void setValue(){
            SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();
            daily_notification.setChecked(sharedPreferences.getBoolean(daily_key, true));
            newest_notification.setChecked(sharedPreferences.getBoolean(newest_key, true));
        }

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            super.onPause();
            getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (key.equals(daily_key)){
                daily_notification.setChecked(sharedPreferences.getBoolean(daily_key, true));
                if (!sharedPreferences.getBoolean(daily_key, true)){
                    AlarmReminder alarmReminder = new AlarmReminder();
                    alarmReminder.cancelAlarm(getContext(), AlarmReminder.DAILY_ID);
                }else {
                    AlarmReminder alarmReminder = new AlarmReminder();
                    alarmReminder.setAlarm(getContext(), "03:07", getResources().getString(R.string.daily_message), AlarmReminder.DAILY_REMINDER,AlarmReminder.DAILY_ID);
                }
            }
            if (key.equals(newest_key)){
                newest_notification.setChecked(sharedPreferences.getBoolean(newest_key, true));
                if (!sharedPreferences.getBoolean(newest_key, true)){
                    AlarmReminder alarmReminder = new AlarmReminder();
                    alarmReminder.cancelAlarm(getContext(), AlarmReminder.RELEASE_ID);
                }else {
                    AlarmReminder alarmReminder = new AlarmReminder();
                    alarmReminder.setAlarm(getContext(), "03:08", getResources().getString(R.string.newest_message), AlarmReminder.RELEASE_REMINDER,AlarmReminder.RELEASE_ID);
                }
            }
        }
    }


}