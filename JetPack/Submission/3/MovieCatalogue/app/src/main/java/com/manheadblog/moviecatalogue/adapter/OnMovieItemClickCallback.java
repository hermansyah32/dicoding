package com.manheadblog.moviecatalogue.adapter;

import com.manheadblog.moviecatalogue.entity.Movie;

public interface OnMovieItemClickCallback {
    void onItemClicked(Movie item);
    void onItemFavorited(Movie item);
}
