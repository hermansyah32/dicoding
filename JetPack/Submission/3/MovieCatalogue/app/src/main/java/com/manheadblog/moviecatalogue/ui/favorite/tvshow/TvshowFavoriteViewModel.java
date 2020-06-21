package com.manheadblog.moviecatalogue.ui.favorite.tvshow;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.manheadblog.moviecatalogue.data.RepositoryData;
import com.manheadblog.moviecatalogue.data.resource.Resource;
import com.manheadblog.moviecatalogue.entity.TVShow;

import java.util.List;

public class TvshowFavoriteViewModel extends ViewModel {
    public ObservableField<Boolean> busy = new ObservableField<>();
    private RepositoryData repositoryData;
    private MutableLiveData<Integer> page = new MutableLiveData<>();

    public TvshowFavoriteViewModel(RepositoryData repositoryData) {
        this.repositoryData = repositoryData;
    }

    LiveData<Resource<List<TVShow>>> tvshows () {
        return repositoryData.getTVShowFavorite();
    }

    LiveData<Resource<PagedList<TVShow>>> tvshowsPaged(){
        return repositoryData.getTVShowFavoritePaged();
    }

    void removeFavorite(TVShow tvShow) {
        if (tvShow != null){
            tvShow.setFavorite(false);
            repositoryData.removeFavoriteTVShow(tvShow);
        }
    }
}
