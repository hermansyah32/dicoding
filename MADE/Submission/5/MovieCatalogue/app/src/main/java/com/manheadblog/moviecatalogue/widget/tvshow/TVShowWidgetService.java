package com.manheadblog.moviecatalogue.widget.tvshow;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class TVShowWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new TVShowWidgetFactory(this.getApplicationContext());
    }
}
