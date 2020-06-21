package com.manheadblog.moviecatalogue.utils;

import com.manheadblog.moviecatalogue.entity.Movie;
import com.manheadblog.moviecatalogue.entity.TVShow;

import java.util.List;

public class FakeDataDummy {
    private static List<Movie> movies;
    private static List<TVShow> tvshows;

    public static List<Movie> generateMovies(ClassLoader classLoader){
        if (movies == null){
            FakeJSONHelper jsonHelper = new FakeJSONHelper(classLoader);
            movies = jsonHelper.loaddMovieDiscover().getResults();
        }
        return movies;
    }

    public static List<TVShow> generateTVShows(ClassLoader classLoader){
        if (tvshows == null){
            FakeJSONHelper jsonHelper = new FakeJSONHelper(classLoader);
            tvshows = jsonHelper.loadTVShowDiscover().getResults();
        }
        return tvshows;
    }

}
