package com.manheadblog.moviecatalogue.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.manheadblog.moviecatalogue.data.remote.MoviePagingResponse;
import com.manheadblog.moviecatalogue.data.remote.RemoteRepository;
import com.manheadblog.moviecatalogue.data.remote.TVShowPagingResponse;
import com.manheadblog.moviecatalogue.entity.Movie;
import com.manheadblog.moviecatalogue.entity.TVShow;

public class RepositoryData implements RepositoryInterface{

    private volatile static RepositoryData instance = null;
    private final RemoteRepository remoteRepository;

    //MutableLiveData
    private MutableLiveData<MoviePagingResponse> movieDiscover;
    private MutableLiveData<TVShowPagingResponse> tvshowDiscover;
    private MutableLiveData<Movie> movieDetail;
    private MutableLiveData<TVShow> tvshowDetail;
    private MutableLiveData<String> errorMessage;

    RepositoryData(@NonNull RemoteRepository remoteRepository){
        this.remoteRepository = remoteRepository;
    }

    public static RepositoryData getInstance(RemoteRepository remoteRepository){
        if (instance == null){
            synchronized (RepositoryData.class){
                if (instance == null){
                    instance = new RepositoryData(remoteRepository);
                }
            }
        }
        return instance;
    }


    @Override
    public LiveData<MoviePagingResponse> getMovieDiscover(int page) {
        if (movieDiscover == null){
            movieDiscover = new MutableLiveData<>();
        }

        remoteRepository.getMoviesDiscover(page, new RemoteRepository.LoadMoviesDiscoverCallback() {
            @Override
            public void onAllMoviesReceived(MoviePagingResponse moviePagingResponse) {
                movieDiscover.postValue(moviePagingResponse);
            }

            @Override
            public void onDataNotAvailable(String message) {
                errorMessage.postValue(message);
            }
        });
        return movieDiscover;
    }

    @Override
    public LiveData<TVShowPagingResponse> getTVShowDiscover(int page) {
        if (tvshowDiscover == null){
            tvshowDiscover = new MutableLiveData<>();
        }

        remoteRepository.getTVShowsDiscover(page, new RemoteRepository.LoadTVShowsDiscoverCallback() {
            @Override
            public void onAllTVShowsReceived(TVShowPagingResponse tvShowPagingResponse) {
                tvshowDiscover.postValue(tvShowPagingResponse);
            }

            @Override
            public void onDataNotAvailable(String message) {
                errorMessage.postValue(message);
            }
        });
        return tvshowDiscover;
    }

    @Override
    public LiveData<Movie> getMovie(int id) {
        if (movieDetail == null){
            movieDetail = new MutableLiveData<>();
        }

        remoteRepository.getMovieDetail(id, new RemoteRepository.LoadMovieDetailCallback() {
            @Override
            public void onMovieDetailReceived(Movie movie) {
                movieDetail.postValue(movie);
            }

            @Override
            public void onDataNotAvailable(String message) {
                errorMessage.postValue(message);
            }
        });
        return movieDetail;
    }

    @Override
    public LiveData<TVShow> getTVShow(int id) {
        if (tvshowDetail == null){
            tvshowDetail = new MutableLiveData<>();
        }

        remoteRepository.getTVShowDetail(id, new RemoteRepository.LoadTVShowDetailCallback() {
            @Override
            public void onTVShowDetailReceived(TVShow tvShow) {
                tvshowDetail.postValue(tvShow);
            }

            @Override
            public void onDataNotAvailable(String message) {
                errorMessage.postValue(message);
            }
        });
        return tvshowDetail;
    }

    @Override
    public LiveData<String> errorMessage() {
        if (errorMessage == null){
            errorMessage = new MutableLiveData<>();
        }
        return errorMessage;
    }
}
