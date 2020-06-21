package com.manheadblog.moviemania.useCase.tvdetail;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.manheadblog.moviemania.api.API;
import com.manheadblog.moviemania.model.TVShow;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class TVShowDetailViewModel extends ViewModel {
    private MutableLiveData<TVShow> TVShow = new MutableLiveData<>();
    private MutableLiveData<Boolean> busy = new MutableLiveData<>();

    public MutableLiveData<Boolean> getBusy() {
        return busy;
    }

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
        busy.setValue(false);
        return TVShow;
    }
}
