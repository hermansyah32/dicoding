package com.manheadblog.moviecatalogue.data.remote;

import com.manheadblog.moviecatalogue.App;
import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.api.API;
import com.manheadblog.moviecatalogue.entity.Movie;
import com.manheadblog.moviecatalogue.entity.TVShow;
import com.manheadblog.moviecatalogue.utils.EspressoIdlingResource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteRepository {

    private static RemoteRepository instance;

    public static RemoteRepository getInstance(){
        if (instance == null){
            instance = new RemoteRepository();
        }

        return instance;
    }

    public void getMoviesDiscover(int page, LoadMoviesDiscoverCallback callback){
        EspressoIdlingResource.increment();
        API.getClient().discoverMovie("en-US", "popularity.desc", page).enqueue(new Callback<MoviePagingResponse>() {
            @Override
            public void onResponse(Call<MoviePagingResponse> call, Response<MoviePagingResponse> response) {
                if (response.isSuccessful()){
                    callback.onAllMoviesReceived(response.body());
                }else {
                    callback.onDataNotAvailable(App.getInstance().getString(R.string.request_failed_load));
                }
                EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(Call<MoviePagingResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onDataNotAvailable(App.getInstance().getString(R.string.request_failed_load));
                EspressoIdlingResource.decrement();
            }
        });

    }

    public void getTVShowsDiscover(int page, final LoadTVShowsDiscoverCallback callback){
        EspressoIdlingResource.increment();
        API.getClient().discoverTV("en-US", "popularity.desc", page).enqueue(new Callback<TVShowPagingResponse>() {
            @Override
            public void onResponse(Call<TVShowPagingResponse> call, Response<TVShowPagingResponse> response) {
                if (response.isSuccessful()){
                    callback.onAllTVShowsReceived(response.body());
                }else {
                    callback.onDataNotAvailable(App.getInstance().getString(R.string.request_failed_load));
                }
                EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(Call<TVShowPagingResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onDataNotAvailable(App.getInstance().getString(R.string.request_failed_load));
                EspressoIdlingResource.decrement();
            }
        });
    }

    public void getMovieDetail(int id, final LoadMovieDetailCallback callback){
        EspressoIdlingResource.increment();
        API.getClient().detailMovie(id,"en-US").enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()){
                    callback.onMovieDetailReceived(response.body());
                }else {
                    callback.onDataNotAvailable(App.getInstance().getString(R.string.request_failed_load));
                }
                EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                t.printStackTrace();
                callback.onDataNotAvailable(App.getInstance().getString(R.string.request_failed_load));
                EspressoIdlingResource.decrement();
            }
        });
    }

    public void getTVShowDetail(int id, final LoadTVShowDetailCallback callback){
        EspressoIdlingResource.increment();
        API.getClient().detailTV(id, "en-US").enqueue(new Callback<TVShow>() {
            @Override
            public void onResponse(Call<TVShow> call, Response<TVShow> response) {
                if (response.isSuccessful()){
                    callback.onTVShowDetailReceived(response.body());
                }else {
                    callback.onDataNotAvailable(App.getInstance().getString(R.string.request_failed_load));
                }
                EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(Call<TVShow> call, Throwable t) {
                t.printStackTrace();
                callback.onDataNotAvailable(App.getInstance().getString(R.string.request_failed_load));
                EspressoIdlingResource.decrement();
            }
        });
    }

    public interface LoadMoviesDiscoverCallback{
        void onAllMoviesReceived(MoviePagingResponse moviePagingResponse);

        void onDataNotAvailable(String message);
    }

    public interface LoadTVShowsDiscoverCallback{
        void onAllTVShowsReceived(TVShowPagingResponse tvShowPagingResponse);

        void onDataNotAvailable(String message);
    }

    public interface LoadMovieDetailCallback{
        void onMovieDetailReceived(Movie movie);

        void onDataNotAvailable(String message);
    }

    public interface LoadTVShowDetailCallback{
        void onTVShowDetailReceived(TVShow tvShow);

        void onDataNotAvailable(String message);
    }
}
