package com.manheadblog.moviecatalogue.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.manheadblog.moviecatalogue.db.MovieHelper;
import com.manheadblog.moviecatalogue.db.TVShowHelper;

import static com.manheadblog.moviecatalogue.db.DatabaseContract.AUTHORITY;
import static com.manheadblog.moviecatalogue.db.DatabaseContract.MovieColumn.CONTENT_URI_MOVIE;
import static com.manheadblog.moviecatalogue.db.DatabaseContract.TABLE_MOVIE;
import static com.manheadblog.moviecatalogue.db.DatabaseContract.TABLE_TVSHOW;
import static com.manheadblog.moviecatalogue.db.DatabaseContract.TVShowColumn.CONTENT_URI_TVSHOW;

public class DatabaseProvider extends ContentProvider {

    private static final int MOVIE = 110;
    private static final int MOVIE_ID = 564;
    private static final int TVSHOW = 867;
    private static final int TVSHOW_ID = 123;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, TABLE_MOVIE, MOVIE);
        uriMatcher.addURI(AUTHORITY, TABLE_MOVIE + "/#", MOVIE_ID);
        uriMatcher.addURI(AUTHORITY, TABLE_TVSHOW, TVSHOW);
        uriMatcher.addURI(AUTHORITY, TABLE_TVSHOW + "/#", TVSHOW_ID);
    }

    private MovieHelper movieHelper;
    private TVShowHelper tvShowHelper;

    @Override
    public boolean onCreate() {
        movieHelper = new MovieHelper(getContext());
        tvShowHelper = new TVShowHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        movieHelper.open();
        tvShowHelper.open();
        Cursor cursor;
        switch (uriMatcher.match(uri)) {
            case MOVIE:
                cursor = movieHelper.providerGetAll();
                break;
            case TVSHOW:
                cursor = tvShowHelper.providerGetAll();
                break;
            default:
                cursor = null;
                break;
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        movieHelper.open();
        tvShowHelper.open();
        long result;
        switch (uriMatcher.match(uri)) {
            case MOVIE:
                result = movieHelper.providerInsert(values);
                return Uri.parse(CONTENT_URI_MOVIE + "/" + result);
            case TVSHOW:
                result = tvShowHelper.providerInsert(values);
                return Uri.parse(CONTENT_URI_TVSHOW + "/" + result);
            default:
                result = 0;
                break;
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        movieHelper.open();
        tvShowHelper.open();
        int result;
        switch (uriMatcher.match(uri)) {
            case MOVIE_ID:
                result = movieHelper.providerDelete(uri.getLastPathSegment());
                break;
            case TVSHOW_ID:
                result = tvShowHelper.providerDelete(uri.getLastPathSegment());
                break;
            default:
                result = 0;
                break;
        }

        return result;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
