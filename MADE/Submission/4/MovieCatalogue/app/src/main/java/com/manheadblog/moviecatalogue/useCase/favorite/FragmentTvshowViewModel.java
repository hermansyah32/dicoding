package com.manheadblog.moviecatalogue.useCase.favorite;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.manheadblog.moviecatalogue.db.TVShowHelper;
import com.manheadblog.moviecatalogue.model.TVShow;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class FragmentTvshowViewModel extends ViewModel {
    private MutableLiveData<ArrayList<TVShow>> tvshowList = new MutableLiveData<>();
    private MutableLiveData<Boolean> busy = new MutableLiveData<>();

    public MutableLiveData<Boolean> getBusy() {
        return busy;
    }

    LiveData<ArrayList<TVShow>> getTVShows() {
        return tvshowList;
    }

    LiveData<ArrayList<TVShow>> setData(){
        new TVLocalLoader(TVShowHelper.getInstance()).execute();
        return tvshowList;
    }

    class TVLocalLoader extends AsyncTask<Void, Void, ArrayList<TVShow>> {
        private WeakReference<TVShowHelper> weaktvshowHelper;


        TVLocalLoader(TVShowHelper tvShowHelper) {
            weaktvshowHelper = new WeakReference<>(tvShowHelper);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<TVShow> doInBackground(Void... voids) {
            return weaktvshowHelper.get().getAll();
        }

        @Override
        protected void onPostExecute(ArrayList<TVShow> tvShows) {
            super.onPostExecute(tvShows);
            tvshowList.postValue(tvShows);
            busy.setValue(false);
        }
    }
}
