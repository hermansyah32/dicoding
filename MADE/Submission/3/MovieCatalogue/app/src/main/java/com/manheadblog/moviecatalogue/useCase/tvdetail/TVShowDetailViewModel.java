package com.manheadblog.moviecatalogue.useCase.tvdetail;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.manheadblog.moviecatalogue.api.API;
import com.manheadblog.moviecatalogue.model.TVShow;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class TVShowDetailViewModel extends ViewModel {
    private MutableLiveData<com.manheadblog.moviecatalogue.model.TVShow> TVShow = new MutableLiveData<>();

    LiveData<TVShow> getData() {
        return TVShow;
    }

    LiveData<TVShow> setData(int id) {
        API.getClient().detailTV(id, "en-US").enqueue(new Callback<TVShow>() {
            @Override
            public void onResponse(Call<TVShow> call, Response<TVShow> response) {
                try {
                    String data;
                    if (response.isSuccessful()) {
                        TVShow.postValue(response.body());
                    } else {
                        data = response.errorBody().string();
                        Log.e(TAG, "onResponse: " + data);
                        Log.d(TAG, "response.raw().request().url();" + response.raw().request().url());
                    }
                } catch (Exception e) {
                    Log.e(TAG, "responseError: " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<TVShow> call, Throwable t) {
                Log.e(TAG, "responseError: " + t.getMessage());
            }
        });
        return TVShow;
    }
}
