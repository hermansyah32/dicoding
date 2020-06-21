package com.manheadblog.moviecatalogue.ui.movie;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.manheadblog.moviecatalogue.data.RepositoryData;
import com.manheadblog.moviecatalogue.data.resource.Resource;
import com.manheadblog.moviecatalogue.entity.Movie;

import java.util.List;

public class MovieDiscoverViewModel extends ViewModel {
    public ObservableField<Boolean> busy = new ObservableField<>();
    private RepositoryData repositoryData;
    private MutableLiveData<Integer> page = new MutableLiveData<>();

    public MovieDiscoverViewModel(RepositoryData repositoryData) {
        this.repositoryData = repositoryData;
    }

    LiveData<Resource<List<Movie>>> movies = Transformations.switchMap(
            page, mPage -> repositoryData.getMovieDiscover(mPage)
    );

    public int getPage() {
        if (page.getValue() == null) return 0;
        return page.getValue();
    }

    public void setPage(int page){
        this.page.setValue(page);
    }

    void setFavorite(Movie movie) {
        repositoryData.setFavoriteMovie(movie);
    }

    void removeFavorite(Movie movie) {
        repositoryData.removeFavoriteMovie(movie);
    }

}
