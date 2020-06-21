package com.manheadblog.moviecatalogue.ui.favorite.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.manheadblog.moviecatalogue.data.RepositoryData;
import com.manheadblog.moviecatalogue.data.resource.Resource;
import com.manheadblog.moviecatalogue.entity.Movie;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieFavoriteViewModelTest {

    private MovieFavoriteViewModel viewModel;
    private RepositoryData repositoryData = mock(RepositoryData.class);
    private int dummyPage = 1;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp(){
        viewModel = new MovieFavoriteViewModel(repositoryData);
    }

    @Test
    public void getData() {
        MutableLiveData<Resource<PagedList<Movie>>> dummyCourse = new MutableLiveData<>();
        PagedList<Movie> pagedList = mock(PagedList.class);

        dummyCourse.setValue(Resource.success(pagedList));

        when(repositoryData.getMovieFavoritePaged()).thenReturn(dummyCourse);

        Observer<Resource<PagedList<Movie>>> observer = mock(Observer.class);

        viewModel.moviesPaged().observeForever(observer);

        verify(observer).onChanged(Resource.success(pagedList));
    }

}