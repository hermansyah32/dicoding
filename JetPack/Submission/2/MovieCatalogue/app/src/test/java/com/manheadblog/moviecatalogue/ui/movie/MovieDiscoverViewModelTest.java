package com.manheadblog.moviecatalogue.ui.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.manheadblog.moviecatalogue.data.RepositoryData;
import com.manheadblog.moviecatalogue.data.remote.MoviePagingResponse;
import com.manheadblog.moviecatalogue.utils.FakeDataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieDiscoverViewModelTest {

    private MovieDiscoverViewModel viewModel;
    private RepositoryData repositoryData = mock(RepositoryData.class);
    private int dummyPage = 1;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp(){
        viewModel = new MovieDiscoverViewModel(repositoryData);
    }

    @Test
    public void getData() {
        MoviePagingResponse movie = FakeDataDummy.generateMovies(getClass().getClassLoader());

        MutableLiveData<MoviePagingResponse> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(movie);

        when(repositoryData.getMovieDiscover(dummyPage)).thenReturn(mutableLiveData);

        Observer<MoviePagingResponse> observer = mock(Observer.class);
        viewModel.getData(dummyPage).observeForever(observer);

        verify(observer).onChanged(movie);
    }
}