package com.manheadblog.moviecatalogue.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.manheadblog.moviecatalogue.App;
import com.manheadblog.moviecatalogue.Utils.GsonUtils;
import com.manheadblog.moviecatalogue.model.GenresItem;
import com.manheadblog.moviecatalogue.model.Movie;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.manheadblog.moviecatalogue.db.DatabaseContract.TABLE_MOVIE;

public class MovieHelper {
    private static final String DATABASE_TABLE = TABLE_MOVIE;
    private static DatabaseHelper databaseHelper;
    private static MovieHelper INSTANCE;
    private static SQLiteDatabase database;

    public MovieHelper(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    public static MovieHelper getInstance(){
        if (INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new MovieHelper(App.getInstance());
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException{
        database = databaseHelper.getWritableDatabase();
    }

    public void close(){
        databaseHelper.close();

        if (database.isOpen()){
            database.close();
        }
    }

    public ArrayList<Movie> getAll(){
        ArrayList<Movie> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,null,
                null,null,_ID + " ASC", null);
        cursor.moveToFirst();
        Movie movie;
        if (cursor.getCount()>0){
            do {
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

                arrayList.add(movie);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }

        cursor.close();
        return arrayList;
    }

    public long insert(Movie movie){
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

        return database.insert(DATABASE_TABLE, null, contentValues);
    }

    public int delete(long id){
        return database.delete(DATABASE_TABLE, _ID + " = '" + id + "'", null);
    }

    /*
     *Provider section
     */
    public Cursor providerGetAll(){
        return database.query(
                DATABASE_TABLE, null, null, null,
                null, null, _ID + " ASC");
    }

    public long providerInsert(ContentValues contentValues){
        return database.insert(DATABASE_TABLE, null, contentValues);
    }

    public int providerDelete(String id){
        return database.delete(DATABASE_TABLE, _ID + " = '" + id + "'", null);
    }
}
