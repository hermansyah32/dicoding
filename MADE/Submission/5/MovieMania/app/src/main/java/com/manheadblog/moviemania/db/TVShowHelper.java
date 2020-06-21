package com.manheadblog.moviemania.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.manheadblog.moviemania.App;

import static android.provider.BaseColumns._ID;
import static com.manheadblog.moviemania.db.DatabaseContract.TABLE_TVSHOW;

public class TVShowHelper {
    private static final String DATABASE_TABLE = TABLE_TVSHOW;
    private static DatabaseHelper databaseHelper;
    private static TVShowHelper INSTANCE;
    private static SQLiteDatabase database;

    public TVShowHelper(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    public static TVShowHelper getInstance(){
        if (INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new TVShowHelper(App.getInstance());
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close(){
        databaseHelper.close();

        if (database.isOpen()){
            database.close();
        }
    }

//    public ArrayList<TVShow> getAll(){
//        ArrayList<TVShow> arrayList = new ArrayList<>();
//        Cursor cursor = database.query(DATABASE_TABLE, null,
//                null,null,
//                null,null,_ID + " ASC", null);
//        cursor.moveToFirst();
//        TVShow tvshow;
//
//        if (cursor.getCount()>0){
//            do {
//                tvshow = new TVShow();
//                tvshow.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
//                tvshow.setName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.NAME)));
//                tvshow.setBackdropPath(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.BACKDROP_PATH)));
//                tvshow.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.POSTER_PATH)));
//                tvshow.setType(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.TYPE)));
//                tvshow.setOriginalName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.ORIGINAL_NAME)));
//                tvshow.setStatus(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.STATUS)));
//                tvshow.setGenres(GsonUtils.getList(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.GENRES)), GenresItem.class));
//                tvshow.setOriginalLanguage(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.ORIGINAL_LANGUAGE)));
//                tvshow.setEpisodeRunTime(GsonUtils.getList(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.EPISODE_RUNTIME)),Integer.class));
//                tvshow.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.OVERVIEW)));
//                tvshow.setPopularity(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.POPULARITY)));
//                tvshow.setNumberOfSeasons(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.NUMBER_OF_SEASONS)));
//                tvshow.setNumberOfEpisodes(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.NUMBER_OF_EPISODES)));
//                tvshow.setLastAirDate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.LAST_AIR_DATE)));
//                tvshow.setFirstAirDate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.FIRST_AIR_DATE)));
//                tvshow.setHomepage(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.HOMEPAGE)));
//                tvshow.setVoteAverage(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.VOTE_AVERAGE)));
//                tvshow.setVoteCount(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.TVShowColumn.VOTE_COUNT)));
//
//                arrayList.add(tvshow);
//                cursor.moveToNext();
//            }while (!cursor.isAfterLast());
//        }
//
//        cursor.close();
//        return arrayList;
//    }
//
//    public long insert(TVShow tvshow){
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(_ID, tvshow.getId());
//        contentValues.put(DatabaseContract.TVShowColumn.NAME, tvshow.getName().replace("'","\\'"));
//        contentValues.put(DatabaseContract.TVShowColumn.OVERVIEW, tvshow.getOverview().replace("'","\\'"));
//        contentValues.put(DatabaseContract.TVShowColumn.ORIGINAL_LANGUAGE, tvshow.getOriginalLanguage());
//        contentValues.put(DatabaseContract.TVShowColumn.ORIGINAL_NAME, tvshow.getOriginalName().replace("'","\\'"));
//        contentValues.put(DatabaseContract.TVShowColumn.TYPE, tvshow.getType());
//        contentValues.put(DatabaseContract.TVShowColumn.POSTER_PATH, tvshow.getPosterPath());
//        contentValues.put(DatabaseContract.TVShowColumn.BACKDROP_PATH, tvshow.getBackdropPath());
//        contentValues.put(DatabaseContract.TVShowColumn.EPISODE_RUNTIME, GsonUtils.toJSON(tvshow.getEpisodeRunTime()));
//        contentValues.put(DatabaseContract.TVShowColumn.NUMBER_OF_EPISODES, tvshow.getNumberOfEpisodes());
//        contentValues.put(DatabaseContract.TVShowColumn.GENRES, GsonUtils.toJSON(tvshow.getGenres()));
//        contentValues.put(DatabaseContract.TVShowColumn.POPULARITY, tvshow.getPopularity());
//        contentValues.put(DatabaseContract.TVShowColumn.VOTE_AVERAGE, tvshow.getVoteAverage());
//        contentValues.put(DatabaseContract.TVShowColumn.NUMBER_OF_SEASONS, tvshow.getNumberOfSeasons());
//        contentValues.put(DatabaseContract.TVShowColumn.VOTE_COUNT, tvshow.getVoteCount());
//        contentValues.put(DatabaseContract.TVShowColumn.LAST_AIR_DATE, tvshow.getLastAirDate());
//        contentValues.put(DatabaseContract.TVShowColumn.FIRST_AIR_DATE, tvshow.getFirstAirDate());
//        contentValues.put(DatabaseContract.TVShowColumn.HOMEPAGE, tvshow.getHomepage());
//        contentValues.put(DatabaseContract.TVShowColumn.STATUS, tvshow.getStatus());
//
//        return database.insert(DATABASE_TABLE, null, contentValues);
//    }
//
//    public int delete(long id){
//        return database.delete(DATABASE_TABLE, _ID + " = '" + id + "'" , null);
//    }

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
