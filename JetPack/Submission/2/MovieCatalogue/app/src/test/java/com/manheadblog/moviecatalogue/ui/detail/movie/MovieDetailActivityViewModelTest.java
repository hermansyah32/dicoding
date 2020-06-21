package com.manheadblog.moviecatalogue.ui.detail.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.manheadblog.moviecatalogue.data.RepositoryData;
import com.manheadblog.moviecatalogue.entity.Movie;
import com.manheadblog.moviecatalogue.utils.FakeDataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieDetailActivityViewModelTest {

    private MovieDetailActivityViewModel viewModel;
    private RepositoryData repositoryData = mock(RepositoryData.class);

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        viewModel = new MovieDetailActivityViewModel(repositoryData);
    }

    @Test
    public void getData() {
        Movie movie = FakeDataDummy.generateMovies(getClass().getClassLoader()).getResults().get(0);

        MutableLiveData<Movie> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(movie);

        when(repositoryData.getMovie(movie.getId())).thenReturn(mutableLiveData);

        Observer<Movie> observer = mock(Observer.class);
        viewModel.getData(movie.getId()).observeForever(observer);

        verify(observer).onChanged(movie);
    }
}