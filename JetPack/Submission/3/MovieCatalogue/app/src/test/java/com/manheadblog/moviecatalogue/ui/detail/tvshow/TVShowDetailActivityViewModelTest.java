package com.manheadblog.moviecatalogue.ui.detail.tvshow;

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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TVShowDetailActivityViewModelTest {

    private TVShowDetailActivityViewModel viewModel;
    private RepositoryData repositoryData = mock(RepositoryData.class);
    private TVShow tvShow = FakeDataDummy.generateTVShows(getClass().getClassLoader()).get(0);
    private int dummyID = tvShow.getId();


    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        viewModel = new TVShowDetailActivityViewModel(repositoryData);
        viewModel.setId(dummyID);
    }

    @Test
    public void getData() {
        Resource<TVShow> resource = Resource.success(tvShow);
        MutableLiveData<Resource<TVShow>> dummyMovie = new MutableLiveData<>();
        dummyMovie.setValue(resource);

        when(repositoryData.getTVShow(dummyID)).thenReturn(dummyMovie);

        Observer<Resource<TVShow>> observer = mock(Observer.class);

        viewModel.tvshow.observeForever(observer);

        verify(observer).onChanged(resource);
    }
}