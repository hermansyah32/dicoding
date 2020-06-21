package com.manheadblog.moviecatalogue.db;

import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {

    public static final String AUTHORITY = "com.manheadblog.moviecatalogue";
    private static final String SCHEMA = "content";

    public static String TABLE_MOVIE = "movie_table";
    public static final class MovieColumn implements BaseColumns {
        public static String OVERVIEW = "overview";
        public static String ORIGINAL_LANGUAGE = "original_language";
        public static String ORIGINAL_TITLE = "original_title";
        public static String RUNTIME = "runtime";
        public static String TITLE = "title";
        public static String POSTER_PATH = "poster_path";
        public static String BACKDROP_PATH = "backdrop_path";
        public static String REVENUE = "revenue";
        public static String RELEASE_DATE = "release_date";
        public static String GENRES = "genres";
        public static String POPULARITY = "popularity";
        public static String VOTE_AVERAGE = "vote_average";
        public static String TAGLINE = "tagline";
        public static String VOTE_COUNT = "vote_count";
        public static String BUDGET = "budget";
        public static String STATUS = "status";

        public static final Uri CONTENT_URI_MOVIE = new Uri.Builder().scheme(SCHEMA)
                .authority(AUTHORITY)
                .appendPath(TABLE_MOVIE)
                .build();
    }

    public static String TABLE_TVSHOW = "tvshow_table";
    public static final class TVShowColumn implements BaseColumns {
        public static String FIRST_AIR_DATE = "first_air_date";
        public static String OVERVIEW = "overview";
        public static String ORIGINAL_LANGUAGE = "original_language";
        public static String NUMBER_OF_EPISODES = "number_of_episode";
        public static String TYPE = "type";
        public static String POSTER_PATH = "poster_path";
        public static String BACKDROP_PATH = "backdrop_path";
        public static String GENRES = "genres";
        public static String ORIGINAL_NAME = "original_name";
        public static String POPULARITY = "popularity";
        public static String VOTE_AVERAGE = "vote_average";
        public static String NAME = "name";
        public static String EPISODE_RUNTIME = "episode_runtime";
        public static String NUMBER_OF_SEASONS = "number_of_seasons";
        public static String LAST_AIR_DATE = "last_air_date";
        public static String VOTE_COUNT = "vote_count";
        public static String HOMEPAGE = "homeppage";
        public static String STATUS = "status";

        public static final Uri CONTENT_URI_TVSHOW = new Uri.Builder().scheme(SCHEMA)
                .authority(AUTHORITY)
                .appendPath(TABLE_TVSHOW)
                .build();
    }
}
