package com.manheadblog.moviecatalogue.widget.movie;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.useCase.moviedetail.MovieDetailActivity;

/**
 * Implementation of App Widget functionality.
 */
public class FavoriteMovieWidget extends AppWidgetProvider {

    public static final String WIDGET_ACTION = "com.manheadblog.moviecatalogue.WIDGET_ACTION";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Intent intent = new Intent(context, MovieWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.favorite_movie_widget);
        remoteViews.setRemoteAdapter(R.id.stackView, intent);
        remoteViews.setEmptyView(R.id.stackView, R.id.textViewEmptyWidget);

        Intent intentMovieDetail = new Intent(context, FavoriteMovieWidget.class);
        intentMovieDetail.setAction(FavoriteMovieWidget.WIDGET_ACTION);
        intentMovieDetail.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);

        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intentMovieDetail, PendingIntent.FLAG_UPDATE_CURRENT);

        remoteViews.setPendingIntentTemplate(R.id.stackView, pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction() != null){
            if (intent.getAction().equals(FavoriteMovieWidget.WIDGET_ACTION)){
                int id = intent.getIntExtra(MovieDetailActivity.MOVIE_ID, 0);
                Intent newIntent = new Intent(context, MovieDetailActivity.class);
                newIntent.putExtra(MovieDetailActivity.MOVIE_ID, id);
                newIntent.putExtra(WIDGET_ACTION, true);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(newIntent);
            }
        }
    }
}

