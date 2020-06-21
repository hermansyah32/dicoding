package com.manheadblog.moviecatalogue.ui.favorite.movie;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.manheadblog.moviecatalogue.data.RepositoryData;
import com.manheadblog.moviecatalogue.data.resource.Resource;
import com.manheadblog.moviecatalogue.entity.Movie;

import java.util.List;

public class MovieFavoriteViewModel extends ViewModel {
    public ObservableField<Boolean> busy = new ObservableField<>();
    private RepositoryData repositoryData;

    public MovieFavoriteViewModel(RepositoryData repositoryData) {
        this.repositoryData = repositoryData;
    }

    LiveData<Resource<List<Movie>>> movies() {
        return repositoryData.getMovieFavorite();
    }

    LiveData<Resource<PagedList<Movie>>> moviesPaged(){
        return repositoryData.getMovieFavoritePaged();
    }

    void removeFavorite(Movie movie) {
        if (movie != null){
            movie.setFavorite(false);
            repositoryData.removeFavoriteMovie(movie);
        }
    }
}
