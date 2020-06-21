package com.manheadblog.moviecatalogue.utils;

import android.app.Application;

import com.google.gson.reflect.TypeToken;
import com.manheadblog.moviecatalogue.data.remote.DiscoverResponse;
import com.manheadblog.moviecatalogue.entity.Movie;
import com.manheadblog.moviecatalogue.entity.TVShow;

import java.io.IOException;
import java.io.InputStream;

public class JSONHelper {

    private Application application;

    public JSONHelper(Application application){
        this.application = application;
    }

    private String readFile(String filename){
        try {
            InputStream inputStream = application.getAssets().open(filename);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            return new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public DiscoverResponse<Movie> loaddMovieDiscover(){
        return GsonUtils.instance().fromJson(readFile("movie_discover.json"), new TypeToken<DiscoverResponse<Movie>>(){}.getType());
    }

    public DiscoverResponse<TVShow> loadTVShowDiscover(){
        return GsonUtils.instance().fromJson(readFile("tvshow_discover.json"), new TypeToken<DiscoverResponse<TVShow>>(){}.getType());
    }
}
