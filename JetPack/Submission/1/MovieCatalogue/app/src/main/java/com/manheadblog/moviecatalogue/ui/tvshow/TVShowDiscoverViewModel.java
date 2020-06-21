package com.manheadblog.moviecatalogue.ui.tvshow;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.manheadblog.moviecatalogue.entity.TVShow;
import com.manheadblog.moviecatalogue.utils.DataDummy;

import java.util.ArrayList;

public class TVShowDiscoverViewModel extends ViewModel {
    public ObservableField<Boolean> busy = new ObservableField<>();
    private MutableLiveData<ArrayList<TVShow>> success = new MutableLiveData<>();

    LiveData<ArrayList<TVShow>> getSuccess(){
        return this.success;
    }

    public ArrayList<TVShow> getData(){
        busy.set(true);
        success.postValue(DataDummy.generateTVShows());
        busy.set(false);
        return DataDummy.generateTVShows();
    }
}
