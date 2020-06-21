package com.manheadblog.moviecatalogue.useCase.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.manheadblog.moviecatalogue.Utils.GsonUtils;
import com.manheadblog.moviecatalogue.api.API;
import com.manheadblog.moviecatalogue.model.TVShow;

import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class TVShowsFragmentViewModel extends ViewModel {
    private int page = 1, total_pages = 0, total_results = 0;
    private MutableLiveData<ArrayList<TVShow>> tvshowList = new MutableLiveData<>();

    int getNextPage() {
        return page;
    }

    int getTotalPages() {
        return total_pages;
    }

    int getTotalResults() {
        return total_results;
    }

    LiveData<ArrayList<TVShow>> getTVShows() {
        return tvshowList;
    }

    LiveData<ArrayList<TVShow>> setData(int current_page) {
        final ArrayList<TVShow> listItems = new ArrayList<>();

        API.getClient().discoverTV("en-US", "popularity.desc", current_page).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String data;
                    if (response.isSuccessful()) {
                        data = response.body().string();
                        JSONObject responseObject = new JSONObject(data);
                        total_pages = responseObject.getInt("total_pages");
                        total_results = responseObject.getInt("total_results");
                        listItems.addAll(new ArrayList<>(GsonUtils.getList(responseObject.getString("results"), TVShow.class)));
                        tvshowList.postValue(listItems);
                        if (page + 1 > total_pages) page++;
                    } else {
                        data = response.errorBody().string();
                        Log.e(TAG, "onResponse: " + data);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "responseError: " + e.getMessage());
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
        return tvshowList;
    }
}
