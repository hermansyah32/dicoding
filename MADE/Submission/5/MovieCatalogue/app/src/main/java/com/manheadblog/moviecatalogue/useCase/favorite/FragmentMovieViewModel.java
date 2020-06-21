package com.manheadblog.moviecatalogue.useCase.favorite;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.manheadblog.moviecatalogue.App;
import com.manheadblog.moviecatalogue.helper.MovieMappingHelper;
import com.manheadblog.moviecatalogue.model.Movie;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.manheadblog.moviecatalogue.db.DatabaseContract.MovieColumn.CONTENT_URI_MOVIE;

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
        new MovieLocalLoader(App.getInstance()).execute();
        return movieList;
    }

    class MovieLocalLoader extends AsyncTask<Void, Void, ArrayList<Movie>> {
        private WeakReference<Context> weakContext;


        MovieLocalLoader(Context context) {
            weakContext = new WeakReference<>(context);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Movie> doInBackground(Void... voids) {
            Context context = weakContext.get();
            return MovieMappingHelper.mapCursorToArrayList(context.getContentResolver().query(CONTENT_URI_MOVIE,null,null, null, null));
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            super.onPostExecute(movies);
            movieList.postValue(movies);
            busy.setValue(false);
        }
    }
}
