package com.manheadblog.moviemania.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "movie_catalogue_db";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY NOT NULL," +
                    " %s TEXT, " +
                    " %s TEXT, " +
                    " %s TEXT, " +
                    " %s INTEGER, " +
                    " %s TEXT, " +
                    " %s TEXT, " +
                    " %s TEXT, " +
                    " %s BIGINT, " +
                    " %s TEXT, " +
                    " %s TEXT, " +
                    " %s DOUBLE, " +
                    " %s DOUBLE, " +
                    " %s TEXT, " +
                    " %s INTEGER, " +
                    " %s BIGINT, " +
                    " %s TEXT)",
            DatabaseContract.TABLE_MOVIE,
            DatabaseContract.MovieColumn._ID,
            DatabaseContract.MovieColumn.OVERVIEW,
            DatabaseContract.MovieColumn.ORIGINAL_LANGUAGE,
            DatabaseContract.MovieColumn.ORIGINAL_TITLE,
            DatabaseContract.MovieColumn.RUNTIME,
            DatabaseContract.MovieColumn.TITLE,
            DatabaseContract.MovieColumn.POSTER_PATH,
            DatabaseContract.MovieColumn.BACKDROP_PATH,
            DatabaseContract.MovieColumn.REVENUE,
            DatabaseContract.MovieColumn.RELEASE_DATE,
            DatabaseContract.MovieColumn.GENRES,
            DatabaseContract.MovieColumn.POPULARITY,
            DatabaseContract.MovieColumn.VOTE_AVERAGE,
            DatabaseContract.MovieColumn.TAGLINE,
            DatabaseContract.MovieColumn.VOTE_COUNT,
            DatabaseContract.MovieColumn.BUDGET,
            DatabaseContract.MovieColumn.STATUS
    );

    private static final String SQL_CREATE_TABLE_TVSHOW = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY NOT NULL," +
                    " %s TEXT, " +
                    " %s TEXT, " +
                    " %s TEXT, " +
                    " %s INTEGER, " +
                    " %s TEXT, " +
                    " %s TEXT, " +
                    " %s TEXT, " +
                    " %s TEXT, " +
                    " %s TEXT, " +
                    " %s DOUBLE, " +
                    " %s DOUBLE, " +
                    " %s TEXT, " +
                    " %s TEXT, " +
                    " %s TNTEGER, " +
                    " %s TEXT, " +
                    " %s INTEGER, " +
                    " %s TEXT, " +
                    " %s TEXT)",
            DatabaseContract.TABLE_TVSHOW,
            DatabaseContract.TVShowColumn._ID,
            DatabaseContract.TVShowColumn.FIRST_AIR_DATE,
            DatabaseContract.TVShowColumn.OVERVIEW,
            DatabaseContract.TVShowColumn.ORIGINAL_LANGUAGE,
            DatabaseContract.TVShowColumn.NUMBER_OF_EPISODES,
            DatabaseContract.TVShowColumn.TYPE,
            DatabaseContract.TVShowColumn.POSTER_PATH,
            DatabaseContract.TVShowColumn.BACKDROP_PATH,
            DatabaseContract.TVShowColumn.GENRES,
            DatabaseContract.TVShowColumn.ORIGINAL_NAME,
            DatabaseContract.TVShowColumn.POPULARITY,
            DatabaseContract.TVShowColumn.VOTE_AVERAGE,
            DatabaseContract.TVShowColumn.NAME,
            DatabaseContract.TVShowColumn.EPISODE_RUNTIME,
            DatabaseContract.TVShowColumn.NUMBER_OF_SEASONS,
            DatabaseContract.TVShowColumn.LAST_AIR_DATE,
            DatabaseContract.TVShowColumn.VOTE_COUNT,
            DatabaseContract.TVShowColumn.HOMEPAGE,
            DatabaseContract.TVShowColumn.STATUS
    );


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
        db.execSQL(SQL_CREATE_TABLE_TVSHOW);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_MOVIE);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_TVSHOW);
        onCreate(db);
    }
}
