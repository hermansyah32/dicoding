package com.manheadblog.moviemania.helper;

import android.content.ContentValues;
import android.database.Cursor;

import com.manheadblog.moviemania.Utils.GsonUtils;
import com.manheadblog.moviemania.db.DatabaseContract;
import com.manheadblog.moviemania.model.GenresItem;
import com.manheadblog.moviemania.model.TVShow;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;

public class TVShowMappingHelper {
    public static ArrayList<TVShow> mapCursorToArrayList(Cursor cursor){
        ArrayList<TVShow> tvShows = new ArrayList<>();
        while (cursor.moveToNext()){
            TVShow tvshow;
            tvshow = new TVShow();
            tvshow.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
            tvshow.setName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.NAME)));
            tvshow.setBackdropPath(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.BACKDROP_PATH)));
            tvshow.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.POSTER_PATH)));
            tvshow.setType(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.TYPE)));
            tvshow.setOriginalName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.ORIGINAL_NAME)));
            tvshow.setStatus(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.STATUS)));
            tvshow.setGenres(GsonUtils.getList(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.GENRES)), GenresItem.class));
            tvshow.setOriginalLanguage(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.ORIGINAL_LANGUAGE)));
            tvshow.setEpisodeRunTime(GsonUtils.getList(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.EPISODE_RUNTIME)),Integer.class));
            tvshow.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.OVERVIEW)));
            tvshow.setPopularity(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.POPULARITY)));
            tvshow.setNumberOfSeasons(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.NUMBER_OF_SEASONS)));
            tvshow.setNumberOfEpisodes(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.NUMBER_OF_EPISODES)));
            tvshow.setLastAirDate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.LAST_AIR_DATE)));
            tvshow.setFirstAirDate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.FIRST_AIR_DATE)));
            tvshow.setHomepage(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.HOMEPAGE)));
            tvshow.setVoteAverage(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.VOTE_AVERAGE)));
            tvshow.setVoteCount(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.VOTE_COUNT)));

            tvShows.add(tvshow);
        }

        return tvShows;
    }

    public static ContentValues mapObjectToContentValues(TVShow tvshow){
        ContentValues contentValues = new ContentValues();
        contentValues.put(_ID, tvshow.getId());
        contentValues.put(DatabaseContract.TVShowColumn.NAME, tvshow.getName().replace("'","\\'"));
        contentValues.put(DatabaseContract.TVShowColumn.OVERVIEW, tvshow.getOverview().replace("'","\\'"));
        contentValues.put(DatabaseContract.TVShowColumn.ORIGINAL_LANGUAGE, tvshow.getOriginalLanguage());
        contentValues.put(DatabaseContract.TVShowColumn.ORIGINAL_NAME, tvshow.getOriginalName().replace("'","\\'"));
        contentValues.put(DatabaseContract.TVShowColumn.TYPE, tvshow.getType());
        contentValues.put(DatabaseContract.TVShowColumn.POSTER_PATH, tvshow.getPosterPath());
        contentValues.put(DatabaseContract.TVShowColumn.BACKDROP_PATH, tvshow.getBackdropPath());
        contentValues.put(DatabaseContract.TVShowColumn.EPISODE_RUNTIME, GsonUtils.toJSON(tvshow.getEpisodeRunTime()));
        contentValues.put(DatabaseContract.TVShowColumn.NUMBER_OF_EPISODES, tvshow.getNumberOfEpisodes());
        contentValues.put(DatabaseContract.TVShowColumn.GENRES, GsonUtils.toJSON(tvshow.getGenres()));
        contentValues.put(DatabaseContract.TVShowColumn.POPULARITY, tvshow.getPopularity());
        contentValues.put(DatabaseContract.TVShowColumn.VOTE_AVERAGE, tvshow.getVoteAverage());
        contentValues.put(DatabaseContract.TVShowColumn.NUMBER_OF_SEASONS, tvshow.getNumberOfSeasons());
        contentValues.put(DatabaseContract.TVShowColumn.VOTE_COUNT, tvshow.getVoteCount());
        contentValues.put(DatabaseContract.TVShowColumn.LAST_AIR_DATE, tvshow.getLastAirDate());
        contentValues.put(DatabaseContract.TVShowColumn.FIRST_AIR_DATE, tvshow.getFirstAirDate());
        contentValues.put(DatabaseContract.TVShowColumn.HOMEPAGE, tvshow.getHomepage());
        contentValues.put(DatabaseContract.TVShowColumn.STATUS, tvshow.getStatus());
        return contentValues;
    }
}
