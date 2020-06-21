package com.manheadblog.moviecatalogue.ui.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.manheadblog.moviecatalogue.data.RepositoryData;
import com.manheadblog.moviecatalogue.data.remote.TVShowPagingResponse;
import com.manheadblog.moviecatalogue.utils.FakeDataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

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
    }

    @Test
    public void getData() {
        TVShowPagingResponse tvshow = FakeDataDummy.generateTVShows(getClass().getClassLoader());

        MutableLiveData<TVShowPagingResponse> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(tvshow);

        when(repositoryData.getTVShowDiscover(dummyPage)).thenReturn(mutableLiveData);

        Observer<TVShowPagingResponse> observer = mock(Observer.class);
        viewModel.getData(dummyPage).observeForever(observer);

        verify(observer).onChanged(tvshow);
    }
}