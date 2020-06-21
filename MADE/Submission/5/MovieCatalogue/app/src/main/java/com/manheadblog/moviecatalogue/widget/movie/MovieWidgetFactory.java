package com.manheadblog.moviecatalogue.widget.movie;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.db.MovieHelper;
import com.manheadblog.moviecatalogue.model.Movie;
import com.manheadblog.moviecatalogue.useCase.moviedetail.MovieDetailActivity;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MovieWidgetFactory implements RemoteViewsService.RemoteViewsFactory {

    private final ArrayList<Movie> movieArrayList = new ArrayList<>();
    private final Context context;
    private MovieHelper movieHelper;

    MovieWidgetFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        movieHelper = MovieHelper.getInstance();
        movieHelper.open();
        movieArrayList.addAll(movieHelper.getAll());
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return movieArrayList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_item);
        FutureTarget<Bitmap> futureTarget = Glide.with(context).asBitmap()
                .load(movieArrayList.get(position).getURLPosterPath()).submit(150, 250);
        try {
            remoteViews.setImageViewBitmap(R.id.imageViewWidget, futureTarget.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        remoteViews.setTextViewText(R.id.textViewScoreWidget, movieArrayList.get(position).getStringVoteAverage());

        Bundle bundle = new Bundle();
        bundle.putInt(MovieDetailActivity.MOVIE_ID, movieArrayList.get(position).getId());
        Intent intent = new Intent();
        intent.putExtras(bundle);
        remoteViews.setOnClickFillInIntent(R.id.imageViewWidget, intent);
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
