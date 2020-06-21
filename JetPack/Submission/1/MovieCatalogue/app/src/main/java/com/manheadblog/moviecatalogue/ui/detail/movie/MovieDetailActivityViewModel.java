package com.manheadblog.moviecatalogue.ui.detail.movie;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.manheadblog.moviecatalogue.entity.Movie;
import com.manheadblog.moviecatalogue.utils.DataDummy;

public class MovieDetailActivityViewModel extends ViewModel {
    public ObservableField<Boolean> busy = new ObservableField<>();
    private MutableLiveData<Movie> success = new MutableLiveData<>();

    LiveData<Movie> getSuccess(){
        return this.success;
    }

    public Movie getData(int id){
        busy.set(true);
        for (int i = 0; i < DataDummy.generateMovies().size(); i++) {
            Movie movie = DataDummy.generateMovies().get(i);
            if (movie.getId() == id){
                success.postValue(movie);
                busy.set(false);
                return movie;
            }
        }
        return null;
    }
}
