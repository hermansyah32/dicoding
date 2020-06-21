package com.manheadblog.moviecatalogue.utils;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.manheadblog.moviecatalogue.data.DataInjection;
import com.manheadblog.moviecatalogue.data.RepositoryData;
import com.manheadblog.moviecatalogue.ui.detail.movie.MovieDetailActivityViewModel;
import com.manheadblog.moviecatalogue.ui.detail.tvshow.TVShowDetailActivityViewModel;
import com.manheadblog.moviecatalogue.ui.favorite.movie.MovieFavoriteViewModel;
import com.manheadblog.moviecatalogue.ui.favorite.tvshow.TvshowFavoriteViewModel;
import com.manheadblog.moviecatalogue.ui.movie.MovieDiscoverViewModel;
import com.manheadblog.moviecatalogue.ui.tvshow.TVShowDiscoverViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static volatile ViewModelFactory instace;

    private final RepositoryData repositoryData;

    private ViewModelFactory(RepositoryData repository){
        repositoryData = repository;
    }

    public static ViewModelFactory getInstance(Application application) {
        if (instace == null){
            synchronized (ViewModelFactory.class){
                if (instace == null) {
                    instace = new ViewModelFactory(DataInjection.provideRepository(application));
                }
            }
        }
        return instace;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass){
        if (modelClass.isAssignableFrom(MovieDiscoverViewModel.class)){
            //noinspection unchecked
            return (T) new MovieDiscoverViewModel(repositoryData);
        } else if (modelClass.isAssignableFrom(TVShowDiscoverViewModel.class)){
            //noinspection unchecked
            return (T) new TVShowDiscoverViewModel(repositoryData);
        } else if (modelClass.isAssignableFrom(MovieDetailActivityViewModel.class)){
            //noinspection unchecked
            return (T) new MovieDetailActivityViewModel(repositoryData);
        } else if (modelClass.isAssignableFrom(TVShowDetailActivityViewModel.class)){
            //noinspection unchecked
            return (T) new TVShowDetailActivityViewModel(repositoryData);
        } else if (modelClass.isAssignableFrom(MovieFavoriteViewModel.class)){
            //noinspection unchecked
            return (T) new MovieFavoriteViewModel(repositoryData);
        } else if (modelClass.isAssignableFrom(TvshowFavoriteViewModel.class)){
            //noinspection unchecked
            return (T) new TvshowFavoriteViewModel(repositoryData);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
