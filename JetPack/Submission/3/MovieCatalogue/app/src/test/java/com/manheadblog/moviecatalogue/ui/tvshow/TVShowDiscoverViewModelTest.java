package com.manheadblog.moviecatalogue.ui.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.manheadblog.moviecatalogue.data.RepositoryData;
import com.manheadblog.moviecatalogue.data.resource.Resource;
import com.manheadblog.moviecatalogue.entity.TVShow;
import com.manheadblog.moviecatalogue.utils.FakeDataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TVShowDiscoverViewModelTest {

    private TVShowDiscoverViewModel viewModel;
    private RepositoryData repositoryData = mock(RepositoryData.class);
    private int dummyPage = 1;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp(){
        viewModel = new TVShowDiscoverViewModel(repositoryData);
        viewModel.setPage(dummyPage);
    }

    @Test
    public void getData() {
        Resource<List<TVShow>> resource = Resource.success(FakeDataDummy.generateTVShows(getClass().getClassLoader()));
        MutableLiveData<Resource<List<TVShow>>> dummyData = new MutableLiveData<>();
        dummyData.setValue(resource);

        when(repositoryData.getTVShowDiscover(dummyPage)).thenReturn(dummyData);

        Observer<Resource<List<TVShow>>> observer = mock(Observer.class);

        viewModel.tvshows.observeForever(observer);

        verify(observer).onChanged(resource);
    }
}