package com.manheadblog.moviecatalogue.data.local.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.manheadblog.moviecatalogue.entity.Movie;
import com.manheadblog.moviecatalogue.entity.TVShow;

@Database(entities = {Movie.class, TVShow.class},
        version = 1,
        exportSchema = false)
public abstract class MovieCatalogueDatabase extends RoomDatabase {

    private static MovieCatalogueDatabase instanse;

    public abstract MovieCatalogueDAO movieCatalogueDAO();

    private static final Object sLock = new Object();

    public static MovieCatalogueDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (instanse == null) {
                instanse = Room.databaseBuilder(context.getApplicationContext(),
                        MovieCatalogueDatabase.class, "moviecatalogue.db")
                        .build();
            }
            return instanse;
        }
    }
}
