package com.manheadblog.moviecatalogue.utils;

import com.manheadblog.moviecatalogue.data.remote.MoviePagingResponse;
import com.manheadblog.moviecatalogue.data.remote.TVShowPagingResponse;

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

    public MoviePagingResponse loaddMovieDiscover(){
        return GsonUtils.parseObject(readFile("movie_discover.json"), MoviePagingResponse.class);
    }

    public TVShowPagingResponse loadTVShowDiscover(){
        return GsonUtils.parseObject(readFile("tvshow_discover.json"), TVShowPagingResponse.class);
    }
}
