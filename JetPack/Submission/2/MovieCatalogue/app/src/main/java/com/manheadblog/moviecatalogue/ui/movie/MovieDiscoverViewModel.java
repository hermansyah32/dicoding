package com.manheadblog.moviecatalogue.ui.movie;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.manheadblog.moviecatalogue.data.RepositoryData;
import com.manheadblog.moviecatalogue.data.remote.MoviePagingResponse;

public class MovieDiscoverViewModel extends ViewModel {
    public ObservableField<Boolean> busy = new ObservableField<>();
    private RepositoryData repositoryData;

    public MovieDiscoverViewModel(RepositoryData repositoryData) {
        this.repositoryData = repositoryData;
    }

    LiveData<MoviePagingResponse> getData(int page){
        return repositoryData.getMovieDiscover(page);
    }
}
