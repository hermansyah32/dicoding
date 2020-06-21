package com.manheadblog.moviecatalogue.widget.tvshow;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.db.TVShowHelper;
import com.manheadblog.moviecatalogue.model.TVShow;
import com.manheadblog.moviecatalogue.useCase.tvdetail.TVShowDetailActivity;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class TVShowWidgetFactory implements RemoteViewsService.RemoteViewsFactory {

    private final ArrayList<TVShow> tvShowsArrayList = new ArrayList<>();
    private final Context context;
    private TVShowHelper tvShowHelper;

    public TVShowWidgetFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        tvShowHelper = TVShowHelper.getInstance();
        tvShowHelper.open();
        tvShowsArrayList.addAll(tvShowHelper.getAll());
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return tvShowsArrayList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_item);
        FutureTarget<Bitmap> futureTarget = Glide.with(context).asBitmap()
                .load(tvShowsArrayList.get(position).getURLPosterPath()).submit(150, 250);
        try {
            remoteViews.setImageViewBitmap(R.id.imageViewWidget, futureTarget.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        remoteViews.setTextViewText(R.id.textViewScoreWidget, tvShowsArrayList.get(position).getStringVoteAverage());

        Bundle bundle = new Bundle();
        bundle.putInt(TVShowDetailActivity.TVSHOW_ID, tvShowsArrayList.get(position).getId());
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
