package com.manheadblog.moviecatalogue.adapter;

import com.manheadblog.moviecatalogue.entity.TVShow;

public interface OnTVShowItemClickCallback {
    void onItemClicked(TVShow item);
    void onItemFavorited(TVShow item);
}
