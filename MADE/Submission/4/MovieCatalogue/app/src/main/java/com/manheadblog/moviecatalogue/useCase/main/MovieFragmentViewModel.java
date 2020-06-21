package com.manheadblog.moviecatalogue.useCase.main;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.manheadblog.moviecatalogue.Utils.GsonUtils;
import com.manheadblog.moviecatalogue.api.API;
import com.manheadblog.moviecatalogue.db.MovieHelper;
import com.manheadblog.moviecatalogue.model.Movie;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MovieFragmentViewModel extends ViewModel {
    private int page = 1, total_pages = 0, total_results = 0;
    private MutableLiveData<ArrayList<Movie>> movieList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Movie>> favoritemovieList = new MutableLiveData<>();

    int getNextPage() {
        return page;
    }

    int getTotalPages() {
        return total_pages;
    }

    int getTotalResults() {
        return total_results;
    }

    LiveData<ArrayList<Movie>> getMovies() {
        return movieList;
    }

    LiveData<ArrayList<Movie>> getLocalMovies(){
        return favoritemovieList;
    }

    LiveData<ArrayList<Movie>> setData(int current_page) {
        final ArrayList<Movie> listItems = new ArrayList<>();

        MovieHelper movieHelper = MovieHelper.getInstance();
        movieHelper.open();
        //new MovieLocalLoader(movieHelper);

        favoritemovieList.postValue(movieHelper.getAll());

        API.getClient().discoverMovie("en-US", "popularity.desc", current_page).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String data;
                    if (response.isSuccessful()) {
                        data = response.body().string();
                        JSONObject responseObject = new JSONObject(data);
                        total_pages = responseObject.getInt("total_pages");
                        total_results = responseObject.getInt("total_results");
                        listItems.addAll(new ArrayList<>(GsonUtils.getList(responseObject.getString("results"), Movie.class)));
                        movieList.postValue(listItems);
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
        return movieList;
    }

    LiveData<ArrayList<Movie>> setDataLocal(){
        new MovieLocalLoader(MovieHelper.getInstance()).execute();
        return favoritemovieList;
    }

    class MovieLocalLoader extends AsyncTask<Void, Void, ArrayList<Movie>> {
        private WeakReference<MovieHelper> weakMovieHelper;


        MovieLocalLoader(MovieHelper movieHelper) {
            weakMovieHelper = new WeakReference<>(movieHelper);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Movie> doInBackground(Void... voids) {
            return weakMovieHelper.get().getAll();
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            super.onPostExecute(movies);
            favoritemovieList.postValue(movies);
        }
    }
}
