package com.manheadblog.moviecatalogue.useCase.favorite;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.manheadblog.moviecatalogue.db.MovieHelper;
import com.manheadblog.moviecatalogue.model.Movie;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class FragmentMovieViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Movie>> movieList = new MutableLiveData<>();
    private MutableLiveData<Boolean> busy = new MutableLiveData<>();

    public MutableLiveData<Boolean> getBusy() {
        return busy;
    }

    LiveData<ArrayList<Movie>> getMovies() {
        return movieList;
    }

    LiveData<ArrayList<Movie>> setData(){
        new MovieLocalLoader(MovieHelper.getInstance()).execute();
        return movieList;
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
            movieList.postValue(movies);
            busy.setValue(false);
        }
    }
}
