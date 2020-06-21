package com.manheadblog.moviecatalogue.ui.detail.tvshow;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.manheadblog.moviecatalogue.data.RepositoryData;
import com.manheadblog.moviecatalogue.entity.TVShow;

public class TVShowDetailActivityViewModel extends ViewModel {
    public ObservableField<Boolean> busy = new ObservableField<>();
    private RepositoryData repositoryData;

    public TVShowDetailActivityViewModel(RepositoryData repositoryData) {
        this.repositoryData = repositoryData;
    }

    LiveData<TVShow> getData(int id){
        return repositoryData.getTVShow(id);
    }
}
