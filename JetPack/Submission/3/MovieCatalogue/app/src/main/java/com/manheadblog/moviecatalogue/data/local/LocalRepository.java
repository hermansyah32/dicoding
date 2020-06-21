package com.manheadblog.moviecatalogue.data.local;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import com.manheadblog.moviecatalogue.data.local.room.MovieCatalogueDAO;
import com.manheadblog.moviecatalogue.entity.Movie;
import com.manheadblog.moviecatalogue.entity.TVShow;

import java.util.List;

public class LocalRepository {

    private final MovieCatalogueDAO movieCatalogueDAO;
    private static LocalRepository instance;

    private LocalRepository(MovieCatalogueDAO databaseDAO) {
        this.movieCatalogueDAO = databaseDAO;
    }

    public static LocalRepository getInstance(MovieCatalogueDAO databaseDAO) {
        if (instance == null) {
            instance = new LocalRepository(databaseDAO);
        }
        return instance;
    }

    public LiveData<List<Movie>> getAllMovies() {
        return movieCatalogueDAO.getMovies();
    }

    public LiveData<List<Movie>> getMoviesFavorite() {
        return movieCatalogueDAO.getMoviesFavorite();
    }

    public DataSource.Factory<Integer, Movie> getMoviesFavoritePaged() {
        return movieCatalogueDAO.getMoviesFavoritePaged();
    }

    public LiveData<Movie> getMovieById(int id){
        return movieCatalogueDAO.getMovieById(id);
    }

    public void insertMovie(Movie movie){
        movieCatalogueDAO.insertMovie(movie);
    }

    public void updateMovie(Movie movie){
        movieCatalogueDAO.updateMovie(movie);
    }

    public void deleteMovie(Movie movie){
        movieCatalogueDAO.deleteMovie(movie);
    }

    public LiveData<List<TVShow>> getAllTVShows() {
        return movieCatalogueDAO.geTVShows();
    }

    public LiveData<List<TVShow>> getTVShowsFavorite() {
        return movieCatalogueDAO.getTVShowFavorites();
    }

    public DataSource.Factory<Integer, TVShow> getTVShowsFavoritePaged() {
        return movieCatalogueDAO.getTVShowFavoritePaged();
    }

    public LiveData<TVShow> getTVShowById(int id){
        return movieCatalogueDAO.getTVShowById(id);
    }

    public void insertTVShow(TVShow tvshow){
        movieCatalogueDAO.insertTVShow(tvshow);
    }

    public void updateTVShow(TVShow tvshow){
        movieCatalogueDAO.updateTVShow(tvshow);
    }

    public void deleteTVShow(TVShow tvshow){
        movieCatalogueDAO.deleteTVShow(tvshow);
    }
}
