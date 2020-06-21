package com.manheadblog.moviecatalogue.data;

import androidx.lifecycle.LiveData;

import com.manheadblog.moviecatalogue.data.remote.MoviePagingResponse;
import com.manheadblog.moviecatalogue.data.remote.TVShowPagingResponse;
import com.manheadblog.moviecatalogue.entity.Movie;
import com.manheadblog.moviecatalogue.entity.TVShow;

public interface RepositoryInterface {

    LiveData<MoviePagingResponse> getMovieDiscover(int page);
    LiveData<TVShowPagingResponse> getTVShowDiscover(int page);

    LiveData<Movie> getMovie(int id);
    LiveData<TVShow> getTVShow(int id);

    LiveData<String> errorMessage();
}
