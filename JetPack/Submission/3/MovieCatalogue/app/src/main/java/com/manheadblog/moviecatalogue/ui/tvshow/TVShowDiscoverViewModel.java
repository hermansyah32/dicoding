package com.manheadblog.moviecatalogue.ui.tvshow;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.manheadblog.moviecatalogue.data.RepositoryData;
import com.manheadblog.moviecatalogue.data.resource.Resource;
import com.manheadblog.moviecatalogue.entity.TVShow;

import java.util.List;

public class TVShowDiscoverViewModel extends ViewModel {
    public ObservableField<Boolean> busy = new ObservableField<>();
    private RepositoryData repositoryData;
    private MutableLiveData<Integer> page = new MutableLiveData<>();

    public TVShowDiscoverViewModel(RepositoryData repositoryData) {
        this.repositoryData = repositoryData;
    }

    LiveData<Resource<List<TVShow>>> tvshows = Transformations.switchMap(
            page, mPage -> repositoryData.getTVShowDiscover(mPage)
    );

    public int getPage() {
        if (page.getValue() == null) return 0;
        return page.getValue();
    }

    public void setPage(int page){
        this.page.setValue(page);
    }

    void setFavorite(TVShow tvShow) {
        repositoryData.setFavoriteTVShow(tvShow);
    }

    void removeFavorite(TVShow tvShow) {
        repositoryData.removeFavoriteTVShow(tvShow);
    }

}
