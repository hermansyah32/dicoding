package com.manheadblog.moviecatalogue.useCase.moviedetail;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.manheadblog.moviecatalogue.api.API;
import com.manheadblog.moviecatalogue.model.Movie;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MovieDetailViewModel extends ViewModel {
    private MutableLiveData<Movie> movie = new MutableLiveData<>();
    private MutableLiveData<Boolean> busy = new MutableLiveData<>();

    public final ObservableField<Movie> movieData = new ObservableField<>();

    public MovieDetailViewModel(){

    }

    public MutableLiveData<Boolean> getBusy() {
        return busy;
    }

    LiveData<Movie> getData() {
        return movie;
    }

    LiveData<Movie> setData(int id) {
        API.getClient().detailMovie(id, "en-US").enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                try {
                    String data;
                    if (response.isSuccessful()) {
                        movie.postValue(response.body());
                    } else {
                        data = response.errorBody().string();
                    }
                } catch (Exception e) {
                    Log.e(TAG, "responseError: " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e(TAG, "responseError: " + t.getMessage());
            }
        });
        busy.setValue(false);
        return movie;
    }
}
