package com.manheadblog.moviecatalogue.ui.detail.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.manheadblog.moviecatalogue.data.RepositoryData;
import com.manheadblog.moviecatalogue.entity.TVShow;
import com.manheadblog.moviecatalogue.utils.FakeDataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TVShowDetailActivityViewModelTest {

    private TVShowDetailActivityViewModel viewModel;
    private RepositoryData repositoryData = mock(RepositoryData.class);
    private int dummyID = 1;


    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        viewModel = new TVShowDetailActivityViewModel(repositoryData);
    }

    @Test
    public void getData() {
        TVShow tvShow = FakeDataDummy.generateTVShows(getClass().getClassLoader()).getResults().get(0);

        MutableLiveData<TVShow> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(tvShow);

        when(repositoryData.getTVShow(tvShow.getId())).thenReturn(mutableLiveData);

        Observer<TVShow> observer = mock(Observer.class);
        viewModel.getData(tvShow.getId()).observeForever(observer);
    }
}