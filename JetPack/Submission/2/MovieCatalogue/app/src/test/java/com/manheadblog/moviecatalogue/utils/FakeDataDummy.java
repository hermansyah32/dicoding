package com.manheadblog.moviecatalogue.utils;

import com.manheadblog.moviecatalogue.data.remote.MoviePagingResponse;
import com.manheadblog.moviecatalogue.data.remote.TVShowPagingResponse;

public class FakeDataDummy {
    private static MoviePagingResponse moviePagingResponse;
    private static TVShowPagingResponse tvShowPagingResponse;

    public static MoviePagingResponse generateMovies(ClassLoader classLoader){
        if (moviePagingResponse == null){
            FakeJSONHelper jsonHelper = new FakeJSONHelper(classLoader);
            moviePagingResponse = jsonHelper.loaddMovieDiscover();
        }
        return moviePagingResponse;
    }

    public static TVShowPagingResponse generateTVShows(ClassLoader classLoader){
        if (tvShowPagingResponse == null){
            FakeJSONHelper jsonHelper = new FakeJSONHelper(classLoader);
            tvShowPagingResponse = jsonHelper.loadTVShowDiscover();
        }
        return tvShowPagingResponse;
    }

}
