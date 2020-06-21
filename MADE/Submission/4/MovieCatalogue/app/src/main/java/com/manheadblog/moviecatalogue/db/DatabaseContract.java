package com.manheadblog.moviecatalogue.db;

import android.provider.BaseColumns;

public class DatabaseContract {
    static String TABLE_MOVIE = "movie_table";
    static final class MovieColumn implements BaseColumns {
        static String ITEM_ID = "item_id";
        static String OVERVIEW = "overview";
        static String ORIGINAL_LANGUAGE = "original_language";
        static String ORIGINAL_TITLE = "original_title";
        static String RUNTIME = "runtime";
        static String TITLE = "title";
        static String POSTER_PATH = "poster_path";
        static String BACKDROP_PATH = "backdrop_path";
        static String REVENUE = "revenue";
        static String RELEASE_DATE = "release_date";
        static String GENRES = "genres";
        static String POPULARITY = "popularity";
        static String VOTE_AVERAGE = "vote_average";
        static String TAGLINE = "tagline";
        static String VOTE_COUNT = "vote_count";
        static String BUDGET = "budget";
        static String STATUS = "status";
    }

    static String TABLE_TVSHOW = "tvshow_table";
    static final class TVShowColumn implements BaseColumns {
        static String ITEM_ID = "item_id";
        static String FIRST_AIR_DATE = "first_air_date";
        static String OVERVIEW = "overview";
        static String ORIGINAL_LANGUAGE = "original_language";
        static String NUMBER_OF_EPISODES = "number_of_episode";
        static String TYPE = "type";
        static String POSTER_PATH = "poster_path";
        static String BACKDROP_PATH = "backdrop_path";
        static String GENRES = "genres";
        static String ORIGINAL_NAME = "original_name";
        static String POPULARITY = "popularity";
        static String VOTE_AVERAGE = "vote_average";
        static String NAME = "name";
        static String EPISODE_RUNTIME = "episode_runtime";
        static String NUMBER_OF_SEASONS = "number_of_seasons";
        static String LAST_AIR_DATE = "last_air_date";
        static String VOTE_COUNT = "vote_count";
        static String HOMEPAGE = "homeppage";
        static String STATUS = "status";
    }
}
