package com.manheadblog.moviecatalogue.data;

import android.app.Application;

import com.manheadblog.moviecatalogue.data.local.LocalRepository;
import com.manheadblog.moviecatalogue.data.local.room.MovieCatalogueDatabase;
import com.manheadblog.moviecatalogue.data.remote.RemoteRepository;
import com.manheadblog.moviecatalogue.utils.AppExecutors;

public class DataInjection {
    public static RepositoryData provideRepository(Application application){

        MovieCatalogueDatabase database = MovieCatalogueDatabase.getInstance(application);

        LocalRepository localRepository = LocalRepository.getInstance(database.movieCatalogueDAO());
        RemoteRepository remoteRepository = RemoteRepository.getInstance();
        AppExecutors appExecutors = new AppExecutors();

        return RepositoryData.getInstance(localRepository, remoteRepository, appExecutors);
    }
}
