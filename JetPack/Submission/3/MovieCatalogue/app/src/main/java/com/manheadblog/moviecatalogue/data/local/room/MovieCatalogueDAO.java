package com.manheadblog.moviecatalogue.data.local.room;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.manheadblog.moviecatalogue.entity.Movie;
import com.manheadblog.moviecatalogue.entity.TVShow;

import java.util.List;

@Dao
public interface MovieCatalogueDAO {

    @WorkerThread
    @Query("SELECT * FROM movies")
    LiveData<List<Movie>> getMovies();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMovie (Movie movie);

    @Update(onConflict = OnConflictStrategy.FAIL)
    int updateMovie (Movie movie);

    @Transaction
    @Query("SELECT * FROM movies WHERE id = :id")
    LiveData<Movie> getMovieById(int id);

    @Transaction
    @Query("SELECT * FROM movies WHERE isFavorite = 1")
    LiveData<List<Movie>> getMoviesFavorite();

    @WorkerThread
    @Query("SELECT * FROM movies where isFavorite = 1")
    DataSource.Factory<Integer, Movie> getMoviesFavoritePaged();

    @Delete
    void deleteMovie (Movie movie);

    @WorkerThread
    @Query("SELECT * FROM tvshows")
    LiveData<List<TVShow>> geTVShows();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTVShow (TVShow tvShow);

    @Update(onConflict = OnConflictStrategy.FAIL)
    int updateTVShow (TVShow tvShow);

    @Transaction
    @Query("SELECT * FROM tvshows WHERE id = :id")
    LiveData<TVShow> getTVShowById(int id);

    @Transaction
    @Query("SELECT * FROM tvshows WHERE isFavorite = 1")
    LiveData<List<TVShow>> getTVShowFavorites();

    @WorkerThread
    @Query("SELECT * FROM tvshows where isFavorite = 1")
    DataSource.Factory<Integer, TVShow> getTVShowFavoritePaged();

    @Delete
    void deleteTVShow (TVShow tvShow);

}
