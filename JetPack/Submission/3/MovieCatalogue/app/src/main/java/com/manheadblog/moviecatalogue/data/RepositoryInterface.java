package com.manheadblog.moviecatalogue.data;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.manheadblog.moviecatalogue.data.resource.Resource;
import com.manheadblog.moviecatalogue.entity.Movie;
import com.manheadblog.moviecatalogue.entity.TVShow;

import java.util.List;

public interface RepositoryInterface {

    LiveData<Resource<List<Movie>>> getMovieDiscover(int page);
    LiveData<Resource<List<TVShow>>> getTVShowDiscover(int page);

    LiveData<Resource<Movie>> getMovie(int id);
    LiveData<Resource<TVShow>> getTVShow(int id);

    LiveData<Resource<List<Movie>>> getMovieFavorite();
    LiveData<Resource<List<TVShow>>> getTVShowFavorite();

    LiveData<Resource<PagedList<Movie>>> getMovieFavoritePaged();
    LiveData<Resource<PagedList<TVShow>>> getTVShowFavoritePaged();

    void setFavoriteMovie(Movie movie);
    void setFavoriteTVShow(TVShow tvShow);
    void removeFavoriteMovie(Movie movie);
    void removeFavoriteTVShow(TVShow tvShow);
}
