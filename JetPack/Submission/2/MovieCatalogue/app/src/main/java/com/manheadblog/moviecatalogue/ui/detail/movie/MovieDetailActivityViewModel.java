package com.manheadblog.moviecatalogue.ui.detail.movie;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.manheadblog.moviecatalogue.data.RepositoryData;
import com.manheadblog.moviecatalogue.entity.Movie;

public class MovieDetailActivityViewModel extends ViewModel {
    public ObservableField<Boolean> busy = new ObservableField<>();
    private RepositoryData repositoryData;

    public MovieDetailActivityViewModel(RepositoryData repositoryData) {
        this.repositoryData = repositoryData;
    }

    LiveData<Movie> getData(int id){
        return repositoryData.getMovie(id);
    }

}
