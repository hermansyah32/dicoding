package com.manheadblog.moviecatalogue.api;

import com.manheadblog.moviecatalogue.model.Movie;
import com.manheadblog.moviecatalogue.model.TVShow;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIRequest {
    //Discover Route
    @GET("discover/movie")
    Call<ResponseBody> discoverMovie(
            @Query("language") String language,
            @Query("sort_by") String sort_by,
            @Query("page") int page
    );

    @GET("discover/tv")
    Call<ResponseBody> discoverTV(
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

    @GET("search/movie")
    Call<ResponseBody> searchMovie(
            @Query("query") String query,
            @Query("page") int page

    );

    @GET("search/tv")
    Call<ResponseBody> searchTVShow(
            @Query("query") String query,
            @Query("page") int page

    );

    @GET("discover/movie")
    Call<ResponseBody> getLatestMovie(
            @Query("primary_release_date.gte") String dateGTE,
            @Query("primary_release_date.lte") String dateLTE

    );

    @GET("discover/tv")
    Call<ResponseBody> getLatestTVShow(
            @Query("primary_release_date.gte") String dateGTE,
            @Query("primary_release_date.lte") String dateLTE

    );
}
