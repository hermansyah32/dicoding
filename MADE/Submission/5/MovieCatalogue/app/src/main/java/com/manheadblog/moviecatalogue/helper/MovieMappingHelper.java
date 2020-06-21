package com.manheadblog.moviecatalogue.helper;

import android.content.ContentValues;
import android.database.Cursor;

import com.manheadblog.moviecatalogue.Utils.GsonUtils;
import com.manheadblog.moviecatalogue.db.DatabaseContract;
import com.manheadblog.moviecatalogue.model.GenresItem;
import com.manheadblog.moviecatalogue.model.Movie;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;

public class MovieMappingHelper {
    public static ArrayList<Movie> mapCursorToArrayList(Cursor cursor){
        ArrayList<Movie> movies = new ArrayList<>();
        while (cursor.moveToNext()){
            Movie movie;
            movie = new Movie();
            movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
            movie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.TITLE)));
            movie.setBackdropPath(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.BACKDROP_PATH)));
            movie.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.POSTER_PATH)));
            movie.setBudget(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.BUDGET)));
            movie.setRevenue(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.REVENUE)));
            movie.setStatus(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.STATUS)));
            movie.setGenres(GsonUtils.getList(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.GENRES)), GenresItem.class));
            movie.setOriginalLanguage(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.ORIGINAL_LANGUAGE)));
            movie.setOriginalTitle(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.ORIGINAL_TITLE)));
            movie.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.OVERVIEW)));
            movie.setPopularity(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.POPULARITY)));
            movie.setRuntime(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.RUNTIME)));
            movie.setReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.RELEASE_DATE)));
            movie.setTagline(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.TAGLINE)));
            movie.setVoteAverage(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.VOTE_AVERAGE)));
            movie.setVoteCount(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.VOTE_COUNT)));

            movies.add(movie);
        }
        return movies;
    }

    public static ContentValues mapObjectToContentValues(Movie movie){
        ContentValues contentValues = new ContentValues();
        contentValues.put(_ID, movie.getId());
        contentValues.put(DatabaseContract.MovieColumn.TITLE, movie.getTitle().replace("'","\\'"));
        contentValues.put(DatabaseContract.MovieColumn.OVERVIEW, movie.getOverview().replace("'","\\'"));
        contentValues.put(DatabaseContract.MovieColumn.ORIGINAL_LANGUAGE, movie.getOriginalLanguage());
        contentValues.put(DatabaseContract.MovieColumn.ORIGINAL_TITLE, movie.getOriginalTitle().replace("'","\\'"));
        contentValues.put(DatabaseContract.MovieColumn.RUNTIME, movie.getRuntime());
        contentValues.put(DatabaseContract.MovieColumn.POSTER_PATH, movie.getPosterPath());
        contentValues.put(DatabaseContract.MovieColumn.BACKDROP_PATH, movie.getBackdropPath());
        contentValues.put(DatabaseContract.MovieColumn.REVENUE, movie.getRevenue());
        contentValues.put(DatabaseContract.MovieColumn.RELEASE_DATE, movie.getReleaseDate());
        contentValues.put(DatabaseContract.MovieColumn.GENRES, GsonUtils.toJSON(movie.getGenres()));
        contentValues.put(DatabaseContract.MovieColumn.POPULARITY, movie.getPopularity());
        contentValues.put(DatabaseContract.MovieColumn.VOTE_AVERAGE, movie.getVoteAverage());
        contentValues.put(DatabaseContract.MovieColumn.TAGLINE, movie.getTagline());
        contentValues.put(DatabaseContract.MovieColumn.VOTE_COUNT, movie.getVoteCount());
        contentValues.put(DatabaseContract.MovieColumn.BUDGET, movie.getBudget());
        contentValues.put(DatabaseContract.MovieColumn.STATUS, movie.getStatus());
        return contentValues;
    }
}
