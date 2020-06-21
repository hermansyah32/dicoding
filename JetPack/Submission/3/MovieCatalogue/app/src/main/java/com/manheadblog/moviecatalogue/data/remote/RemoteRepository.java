package com.manheadblog.moviecatalogue.data.remote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.manheadblog.moviecatalogue.api.API;
import com.manheadblog.moviecatalogue.entity.Movie;
import com.manheadblog.moviecatalogue.entity.TVShow;
import com.manheadblog.moviecatalogue.utils.EspressoIdlingResource;
import com.manheadblog.moviecatalogue.utils.GsonUtils;

import java.io.IOException;
import java.util.List;

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

    public LiveData<APIResponse<List<Movie>>> getMoviesDiscover(int page){
        EspressoIdlingResource.increment();
        MutableLiveData<APIResponse<List<Movie>>> resultResponse = new MutableLiveData<>();
        API.getClient().discoverMovie("en-US", "popularity.desc", page).enqueue(new Callback<DiscoverResponse<Movie>>() {
            @Override
            public void onResponse(Call<DiscoverResponse<Movie>> call, Response<DiscoverResponse<Movie>> response) {
                if (response.isSuccessful()){
                    DiscoverResponse<Movie> model = response.body();
                    resultResponse.setValue(APIResponse.success(model.getResults()));
                }else {
                    try {
                        String errorBody = response.errorBody().string();
                        APIResponse modelError = GsonUtils.parseObject(errorBody, APIResponse.class);
                        resultResponse.setValue(APIResponse.error(modelError.status_message,modelError.status_code,null));
                    } catch (IOException e) {
                        e.printStackTrace();
                        resultResponse.setValue(APIResponse.error(e.getMessage(),1001,null));
                    }
                }
                EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(Call<DiscoverResponse<Movie>> call, Throwable t) {
                t.printStackTrace();
                resultResponse.setValue(APIResponse.error(t.getMessage(),501,null));
                EspressoIdlingResource.decrement();
            }
        });
        return resultResponse;
    }

    public LiveData<APIResponse<List<TVShow>>> getTVShowsDiscover(int page){
        EspressoIdlingResource.increment();
        MutableLiveData<APIResponse<List<TVShow>>> resultResponse = new MutableLiveData<>();
        API.getClient().discoverTV("en-US", "popularity.desc", page).enqueue(new Callback<DiscoverResponse<TVShow>>() {
            @Override
            public void onResponse(Call<DiscoverResponse<TVShow>> call, Response<DiscoverResponse<TVShow>> response) {
                if (response.isSuccessful()){
                    DiscoverResponse<TVShow> model = response.body();
                    resultResponse.setValue(APIResponse.success(model.getResults()));
                }else {
                    try {
                        String errorBody = response.errorBody().string();
                        APIResponse modelError = GsonUtils.parseObject(errorBody, APIResponse.class);
                        resultResponse.setValue(APIResponse.error(modelError.status_message,modelError.status_code,null));
                    } catch (IOException e) {
                        e.printStackTrace();
                        resultResponse.setValue(APIResponse.error(e.getMessage(),1001,null));
                    }
                }
                EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(Call<DiscoverResponse<TVShow>> call, Throwable t) {
                t.printStackTrace();
                resultResponse.setValue(APIResponse.error(t.getMessage(),501,null));
                EspressoIdlingResource.decrement();
            }
        });
        return resultResponse;
    }

    public LiveData<APIResponse<Movie>> getMovieDetail(int id){
        EspressoIdlingResource.increment();
        MutableLiveData<APIResponse<Movie>> resultResponse = new MutableLiveData<>();
        API.getClient().detailMovie(id,"en-US").enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()){
                    Movie model = response.body();
                    resultResponse.setValue(APIResponse.success(model));
                }else {
                    try {
                        String errorBody = response.errorBody().string();
                        APIResponse modelError = GsonUtils.parseObject(errorBody, APIResponse.class);
                        resultResponse.setValue(APIResponse.error(modelError.status_message,modelError.status_code,null));
                    } catch (IOException e) {
                        e.printStackTrace();
                        resultResponse.setValue(APIResponse.error(e.getMessage(),1001,null));
                    }
                }
                EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                t.printStackTrace();
                resultResponse.setValue(APIResponse.error(t.getMessage(),501,null));
                EspressoIdlingResource.decrement();
            }
        });
        return resultResponse;
    }

    public LiveData<APIResponse<TVShow>> getTVShowDetail(int id){
        EspressoIdlingResource.increment();
        MutableLiveData<APIResponse<TVShow>> resultResponse = new MutableLiveData<>();
        API.getClient().detailTV(id, "en-US").enqueue(new Callback<TVShow>() {
            @Override
            public void onResponse(Call<TVShow> call, Response<TVShow> response) {
                if (response.isSuccessful()){
                    TVShow model = response.body();
                    resultResponse.setValue(APIResponse.success(model));
                }else {
                    try {
                        String errorBody = response.errorBody().string();
                        APIResponse modelError = GsonUtils.parseObject(errorBody, APIResponse.class);
                        resultResponse.setValue(APIResponse.error(modelError.status_message,modelError.status_code,null));
                    } catch (IOException e) {
                        e.printStackTrace();
                        resultResponse.setValue(APIResponse.error(e.getMessage(),1001,null));
                    }
                }
                EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(Call<TVShow> call, Throwable t) {
                t.printStackTrace();
                resultResponse.setValue(APIResponse.error(t.getMessage(),501,null));
                EspressoIdlingResource.decrement();
            }
        });
        return resultResponse;
    }
}
