package com.manheadblog.moviecatalogue.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.Utils.NotificationUtils;
import com.manheadblog.moviecatalogue.useCase.main.MainActivity;
import com.manheadblog.moviecatalogue.useCase.release.ReleaseActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AlarmReminder extends BroadcastReceiver {

    private static final String MESSAGE = "MESSAGE";
    private static final String TIME_FORMAT  = "HH:mm";
    public static final int DAILY_ID = 678;
    public static final int RELEASE_ID = 978;

    public static final String REMINDER_TYPE = "REMINDER_TYPE";
    public static final String DAILY_REMINDER = "DAILY_REMINDER";
    public static final String RELEASE_REMINDER = "RELEASE_REMINDER";

    public AlarmReminder() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String title = context.getResources().getString(R.string.app_name);
        if (intent.getStringExtra(REMINDER_TYPE).equalsIgnoreCase(DAILY_REMINDER)){
            String message = intent.getStringExtra(MESSAGE);
            Intent newIntent = new Intent(context, MainActivity.class);
            newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,newIntent,PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationUtils.sendNotification(context, pendingIntent, title, message, DAILY_ID);
        }else {
            String message = intent.getStringExtra(MESSAGE);
            Intent newIntent = new Intent(context, ReleaseActivity.class);
            newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,newIntent,PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationUtils.sendNotification(context, pendingIntent, title, message, RELEASE_ID);
        }

    }

    public void setAlarm(Context context, String time, String message, String type, int alarmID){
        if (isInvalidTime(time, TIME_FORMAT)) return;

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReminder.class);
        intent.putExtra(MESSAGE, message);
        intent.putExtra(REMINDER_TYPE, type);

        String timeArray[] = time.split(":");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND, 0);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, alarmID, intent, 0);
        if (alarmManager!=null){
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent);
        }

    }

    private boolean isInvalidTime(String time, String format){
        try {
            DateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
            dateFormat.setLenient(false);
            dateFormat.parse(time);
            return false;
        } catch (ParseException e) {
            return true;
        }
    }

    public void cancelAlarm(Context context, int alarmID){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReminder.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, alarmID, intent, 0);
        pendingIntent.cancel();
        if (alarmManager != null){
            alarmManager.cancel(pendingIntent);
        }
    }
}
