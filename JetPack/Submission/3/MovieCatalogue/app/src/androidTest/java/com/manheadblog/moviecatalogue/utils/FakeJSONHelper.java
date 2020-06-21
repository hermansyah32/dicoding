package com.manheadblog.moviecatalogue.utils;

import com.google.gson.reflect.TypeToken;
import com.manheadblog.moviecatalogue.data.remote.DiscoverResponse;
import com.manheadblog.moviecatalogue.entity.Movie;
import com.manheadblog.moviecatalogue.entity.TVShow;

import java.io.IOException;
import java.io.InputStream;

public class FakeJSONHelper {

    private ClassLoader classLoader;

    public FakeJSONHelper( ClassLoader classLoader){
        this.classLoader = classLoader;
    }

    private String readFile(String filename){
        try {
            InputStream inputStream = classLoader.getResourceAsStream(filename);
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
