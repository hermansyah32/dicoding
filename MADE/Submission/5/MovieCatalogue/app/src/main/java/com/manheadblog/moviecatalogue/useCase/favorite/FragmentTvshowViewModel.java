package com.manheadblog.moviecatalogue.useCase.favorite;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.manheadblog.moviecatalogue.App;
import com.manheadblog.moviecatalogue.helper.TVShowMappingHelper;
import com.manheadblog.moviecatalogue.model.TVShow;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.manheadblog.moviecatalogue.db.DatabaseContract.TVShowColumn.CONTENT_URI_TVSHOW;

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
        new TVLocalLoader(App.getInstance()).execute();
        return tvshowList;
    }

    class TVLocalLoader extends AsyncTask<Void, Void, ArrayList<TVShow>> {
        private WeakReference<Context> weakContext;


        TVLocalLoader(Context context) {
            weakContext = new WeakReference<>(context);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<TVShow> doInBackground(Void... voids) {
            Context context = weakContext.get();
            return TVShowMappingHelper.mapCursorToArrayList(context.getContentResolver().query(CONTENT_URI_TVSHOW,null,null, null, null));
        }

        @Override
        protected void onPostExecute(ArrayList<TVShow> tvShows) {
            super.onPostExecute(tvShows);
            tvshowList.postValue(tvShows);
            busy.setValue(false);
        }
    }
}
