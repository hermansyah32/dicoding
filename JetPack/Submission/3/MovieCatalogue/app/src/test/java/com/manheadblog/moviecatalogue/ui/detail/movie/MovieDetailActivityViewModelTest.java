package com.manheadblog.moviecatalogue.ui.detail.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.manheadblog.moviecatalogue.data.RepositoryData;
import com.manheadblog.moviecatalogue.data.resource.Resource;
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
    private Movie movie = FakeDataDummy.generateMovies(getClass().getClassLoader()).get(0);
    private int dummyID = movie.getId();

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        viewModel = new MovieDetailActivityViewModel(repositoryData);
        viewModel.setId(dummyID);
    }

    @Test
    public void getData() {
        Resource<Movie> resource = Resource.success(movie);
        MutableLiveData<Resource<Movie>> dummyMovie = new MutableLiveData<>();
        dummyMovie.setValue(resource);

        when(repositoryData.getMovie(dummyID)).thenReturn(dummyMovie);

        Observer<Resource<Movie>> observer = mock(Observer.class);

        viewModel.movie.observeForever(observer);

        verify(observer).onChanged(resource);
    }
}