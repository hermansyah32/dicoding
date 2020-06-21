package com.manheadblog.moviecatalogue.ui.movie;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.manheadblog.moviecatalogue.entity.Movie;
import com.manheadblog.moviecatalogue.utils.DataDummy;

import java.util.ArrayList;

public class MovieDiscoverViewModel extends ViewModel {
    public ObservableField<Boolean> busy = new ObservableField<>();
    private MutableLiveData<ArrayList<Movie>> success = new MutableLiveData<>();

    LiveData<ArrayList<Movie>> getSuccess(){
        return this.success;
    }

    public ArrayList<Movie> getData(){
        busy.set(true);
        success.postValue(DataDummy.generateMovies());
        busy.set(false);
        return DataDummy.generateMovies();
    }
}
