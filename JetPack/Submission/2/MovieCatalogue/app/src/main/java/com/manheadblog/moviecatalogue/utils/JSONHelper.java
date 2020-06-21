package com.manheadblog.moviecatalogue.utils;

import android.app.Application;

import com.manheadblog.moviecatalogue.data.remote.MoviePagingResponse;
import com.manheadblog.moviecatalogue.data.remote.TVShowPagingResponse;

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

    public MoviePagingResponse loaddMovieDiscover(){
        return GsonUtils.parseObject(readFile("movie_discover.json"), MoviePagingResponse.class);
    }

    public TVShowPagingResponse loadTVShowDiscover(){
        return GsonUtils.parseObject(readFile("tvshow_discover.json"), TVShowPagingResponse.class);
    }
}
