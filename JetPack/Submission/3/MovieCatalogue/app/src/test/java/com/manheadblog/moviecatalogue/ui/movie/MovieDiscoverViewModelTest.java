package com.manheadblog.moviecatalogue.ui.movie;

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

import java.util.List;

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
        viewModel.setPage(dummyPage);
    }

    @Test
    public void getData() {
        Resource<List<Movie>> resource = Resource.success(FakeDataDummy.generateMovies(getClass().getClassLoader()));
        MutableLiveData<Resource<List<Movie>>> dummyMovie = new MutableLiveData<>();
        dummyMovie.setValue(resource);

        when(repositoryData.getMovieDiscover(dummyPage)).thenReturn(dummyMovie);

        Observer<Resource<List<Movie>>> observer = mock(Observer.class);

        viewModel.movies.observeForever(observer);

        verify(observer).onChanged(resource);
    }
}