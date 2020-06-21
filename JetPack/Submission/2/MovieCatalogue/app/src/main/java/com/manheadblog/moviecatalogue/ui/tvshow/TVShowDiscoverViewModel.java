package com.manheadblog.moviecatalogue.ui.tvshow;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.manheadblog.moviecatalogue.data.RepositoryData;
import com.manheadblog.moviecatalogue.data.remote.TVShowPagingResponse;

public class TVShowDiscoverViewModel extends ViewModel {
    public ObservableField<Boolean> busy = new ObservableField<>();
    private RepositoryData repositoryData;

    public TVShowDiscoverViewModel(RepositoryData repositoryData) {
        this.repositoryData = repositoryData;
    }

    LiveData<TVShowPagingResponse> getData(int page){
        return repositoryData.getTVShowDiscover(page);
    }

}
