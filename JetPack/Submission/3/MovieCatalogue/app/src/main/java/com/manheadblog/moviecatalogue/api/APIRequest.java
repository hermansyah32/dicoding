package com.manheadblog.moviecatalogue.api;

import com.manheadblog.moviecatalogue.data.remote.DiscoverResponse;
import com.manheadblog.moviecatalogue.entity.Movie;
import com.manheadblog.moviecatalogue.entity.TVShow;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIRequest {
    //Discover Route
    @GET("discover/movie")
    Call<DiscoverResponse<Movie>> discoverMovie(
            @Query("language") String language,
            @Query("sort_by") String sort_by,
            @Query("page") int page
    );

    @GET("discover/tv")
    Call<DiscoverResponse<TVShow>> discoverTV(
            @Query("language") String language,
            @Query("sort_by") String sort_by,
            @Query("page") int page
    );

    @GET("movie/{id}")
    Call<Movie> detailMovie(
            @Path("id") int id,
            @Query("language") String language
    );

    @GET("tv/{id}")
    Call<TVShow> detailTV(
            @Path("id") int id,
            @Query("language") String language

    );
}
