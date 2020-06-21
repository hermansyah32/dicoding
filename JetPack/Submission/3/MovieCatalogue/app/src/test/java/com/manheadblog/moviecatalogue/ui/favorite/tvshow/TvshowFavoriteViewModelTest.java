package com.manheadblog.moviecatalogue.ui.favorite.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.manheadblog.moviecatalogue.data.RepositoryData;
import com.manheadblog.moviecatalogue.data.resource.Resource;
import com.manheadblog.moviecatalogue.entity.TVShow;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TvshowFavoriteViewModelTest {

    private TvshowFavoriteViewModel viewModel;
    private RepositoryData repositoryData = mock(RepositoryData.class);
    private int dummyPage = 1;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp(){
        viewModel = new TvshowFavoriteViewModel(repositoryData);
    }

    @Test
    public void getData() {
        MutableLiveData<Resource<PagedList<TVShow>>> dummyCourse = new MutableLiveData<>();
        PagedList<TVShow> pagedList = mock(PagedList.class);

        dummyCourse.setValue(Resource.success(pagedList));

        when(repositoryData.getTVShowFavoritePaged()).thenReturn(dummyCourse);

        Observer<Resource<PagedList<TVShow>>> observer = mock(Observer.class);

        viewModel.tvshowsPaged().observeForever(observer);

        verify(observer).onChanged(Resource.success(pagedList));
    }

}