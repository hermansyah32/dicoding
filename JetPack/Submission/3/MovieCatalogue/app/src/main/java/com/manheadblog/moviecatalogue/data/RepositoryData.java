package com.manheadblog.moviecatalogue.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.manheadblog.moviecatalogue.data.local.LocalRepository;
import com.manheadblog.moviecatalogue.data.remote.APIResponse;
import com.manheadblog.moviecatalogue.data.remote.RemoteRepository;
import com.manheadblog.moviecatalogue.data.resource.Resource;
import com.manheadblog.moviecatalogue.entity.Movie;
import com.manheadblog.moviecatalogue.entity.TVShow;
import com.manheadblog.moviecatalogue.utils.AppExecutors;

import java.util.List;

public class RepositoryData implements RepositoryInterface{

    private volatile static RepositoryData instance = null;
    private final RemoteRepository remoteRepository;
    private final LocalRepository localRepository;
    private  final AppExecutors appExecutors;

    RepositoryData(@NonNull LocalRepository localRepository, @NonNull RemoteRepository remoteRepository, AppExecutors appExecutors){
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
        this.appExecutors = appExecutors;
    }

    public static RepositoryData getInstance(LocalRepository localRepository, RemoteRepository remoteRepository, AppExecutors appExecutors){
        if (instance == null){
            synchronized (RepositoryData.class){
                if (instance == null){
                    instance = new RepositoryData(localRepository, remoteRepository, appExecutors);
                }
            }
        }
        return instance;
    }


    @Override
    public LiveData<Resource<List<Movie>>> getMovieDiscover(int page) {
        return new NetworkBoundResource<List<Movie>, List<Movie>>(appExecutors) {

            @Override
            protected LiveData<List<Movie>> loadFromDB() {
                return localRepository.getAllMovies();
            }

            @Override
            protected Boolean shouldFetch(List<Movie> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<APIResponse<List<Movie>>> createCall() {
                return remoteRepository.getMoviesDiscover(page);
            }

            @Override
            protected void saveCallResult(List<Movie> data) {
                for (Movie movie: data) {
                    localRepository.insertMovie(movie);
                }
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<List<TVShow>>> getTVShowDiscover(int page) {
        return new NetworkBoundResource<List<TVShow>, List<TVShow>>(appExecutors) {

            @Override
            protected LiveData<List<TVShow>> loadFromDB() {
                return localRepository.getAllTVShows();
            }

            @Override
            protected Boolean shouldFetch(List<TVShow> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<APIResponse<List<TVShow>>> createCall() {
                return remoteRepository.getTVShowsDiscover(page);
            }

            @Override
            protected void saveCallResult(List<TVShow> data) {
                for (TVShow tvShow: data) {
                    localRepository.insertTVShow(tvShow);
                }
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<Movie>> getMovie(int id) {
        return new NetworkBoundResource<Movie, Movie>(appExecutors) {

            @Override
            protected LiveData<Movie> loadFromDB() {
                return localRepository.getMovieById(id);
            }

            @Override
            protected Boolean shouldFetch(Movie data) {
                return (data == null);
            }

            @Override
            protected LiveData<APIResponse<Movie>> createCall() {
                return remoteRepository.getMovieDetail(id);
            }

            @Override
            protected void saveCallResult(Movie data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<TVShow>> getTVShow(int id) {
        return new NetworkBoundResource<TVShow, TVShow>(appExecutors) {

            @Override
            protected LiveData<TVShow> loadFromDB() {
                return localRepository.getTVShowById(id);
            }

            @Override
            protected Boolean shouldFetch(TVShow data) {
                return (data == null);
            }

            @Override
            protected LiveData<APIResponse<TVShow>> createCall() {
                return remoteRepository.getTVShowDetail(id);
            }

            @Override
            protected void saveCallResult(TVShow data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<List<Movie>>> getMovieFavorite() {
        return new NetworkBoundResource<List<Movie>, List<Movie>>(appExecutors) {

            @Override
            protected LiveData<List<Movie>> loadFromDB() {
                return localRepository.getMoviesFavorite();
            }

            @Override
            protected Boolean shouldFetch(List<Movie> data) {
                return false;
            }

            @Override
            protected LiveData<APIResponse<List<Movie>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<Movie> data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<List<TVShow>>> getTVShowFavorite() {
        return new NetworkBoundResource<List<TVShow>, List<TVShow>>(appExecutors){

            @Override
            protected LiveData<List<TVShow>> loadFromDB() {
                return localRepository.getTVShowsFavorite();
            }

            @Override
            protected Boolean shouldFetch(List<TVShow> data) {
                return false;
            }

            @Override
            protected LiveData<APIResponse<List<TVShow>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<TVShow> data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<Movie>>> getMovieFavoritePaged() {
        return new NetworkBoundResource<PagedList<Movie>, List<Movie>>(appExecutors) {

            @Override
            protected LiveData<PagedList<Movie>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.getMoviesFavoritePaged(), /* page size */ 5).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<Movie> data) {
                return false;
            }

            @Override
            protected LiveData<APIResponse<List<Movie>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<Movie> data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<TVShow>>> getTVShowFavoritePaged() {
        return new NetworkBoundResource<PagedList<TVShow>, List<TVShow>>(appExecutors) {

            @Override
            protected LiveData<PagedList<TVShow>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.getTVShowsFavoritePaged(), /* page size */ 5).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<TVShow> data) {
                return false;
            }

            @Override
            protected LiveData<APIResponse<List<TVShow>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<TVShow> data) {

            }
        }.asLiveData();
    }

    @Override
    public void setFavoriteMovie(Movie movie) {
        movie.setFavorite(true);
        Runnable runnable = () -> localRepository.updateMovie(movie);
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void setFavoriteTVShow(TVShow tvShow) {
        tvShow.setFavorite(true);
        Runnable runnable = () -> localRepository.updateTVShow(tvShow);
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void removeFavoriteMovie(Movie movie) {
        movie.setFavorite(false);
        Runnable runnable = () -> localRepository.deleteMovie(movie);
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void removeFavoriteTVShow(TVShow tvShow) {
        tvShow.setFavorite(false);
        Runnable runnable = () -> localRepository.deleteTVShow(tvShow);
        appExecutors.diskIO().execute(runnable);
    }
}
