package com.manheadblog.moviecatalogue.data.source;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.manheadblog.moviecatalogue.data.local.LocalRepository;
import com.manheadblog.moviecatalogue.data.remote.RemoteRepository;
import com.manheadblog.moviecatalogue.data.resource.Resource;
import com.manheadblog.moviecatalogue.entity.Movie;
import com.manheadblog.moviecatalogue.entity.TVShow;
import com.manheadblog.moviecatalogue.utils.FakeDataDummy;
import com.manheadblog.moviecatalogue.utils.InstantAppExecutors;
import com.manheadblog.moviecatalogue.utils.LiveDataTestUtil;
import com.manheadblog.moviecatalogue.utils.PagedListUtil;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RepositoryDataTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private LocalRepository localRepository = mock(LocalRepository.class);
    private RemoteRepository remoteRepository = mock(RemoteRepository.class);
    private InstantAppExecutors instantAppExecutors = mock(InstantAppExecutors.class);
    private FakeRepositoryData repositoryData = new FakeRepositoryData(localRepository, remoteRepository, instantAppExecutors);

    private int dummyPage = 1;
    private List<Movie> moviesResponse = FakeDataDummy.generateMovies(getClass().getClassLoader());
    private List<TVShow> tvshowsResponse = FakeDataDummy.generateTVShows(getClass().getClassLoader());
    private Movie movieResponse = moviesResponse.get(0);
    private TVShow tvShowResponse = tvshowsResponse.get(0);

    private int dummyMovieID = movieResponse.getId();
    private int dummyTVShowID = tvShowResponse.getId();

    @Test
    public void getMovieDiscover(){
        MutableLiveData<List<Movie>> dummyData = new MutableLiveData<>();
        dummyData.setValue(FakeDataDummy.generateMovies(getClass().getClassLoader()));

        when(localRepository.getAllMovies()).thenReturn(dummyData);

        Resource<List<Movie>> result = LiveDataTestUtil.getValue(repositoryData.getMovieDiscover(dummyPage));

        verify(localRepository).getAllMovies();
        assertNotNull(result.data);
        assertEquals(moviesResponse.size(), result.data.size());
    }

    @Test
    public void getTVShowDiscover(){
        MutableLiveData<List<TVShow>> dummyData = new MutableLiveData<>();
        dummyData.setValue(FakeDataDummy.generateTVShows(getClass().getClassLoader()));

        when(localRepository.getAllTVShows()).thenReturn(dummyData);

        Resource<List<TVShow>> result = LiveDataTestUtil.getValue(repositoryData.getTVShowDiscover(dummyPage));

        verify(localRepository).getAllTVShows();
        assertNotNull(result.data);
        assertEquals(moviesResponse.size(), result.data.size());
    }

    @Test
    public void getMovieDetail(){
        MutableLiveData<Movie> dummyData = new MutableLiveData<>();
        dummyData.setValue(movieResponse);

        when(localRepository.getMovieById(dummyMovieID)).thenReturn(dummyData);

        Resource<Movie> result = LiveDataTestUtil.getValue(repositoryData.getMovie(dummyMovieID));

        verify(localRepository).getMovieById(dummyMovieID);
        assertNotNull(result.data);
        assertEquals(movieResponse.getTitle(), result.data.getTitle());
    }

    @Test
    public void getTVShowDetail(){
        MutableLiveData<TVShow> dummyData = new MutableLiveData<>();
        dummyData.setValue(tvShowResponse);

        when(localRepository.getTVShowById(dummyTVShowID)).thenReturn(dummyData);

        Resource<TVShow> result = LiveDataTestUtil.getValue(repositoryData.getTVShow(dummyTVShowID));

        verify(localRepository).getTVShowById(dummyTVShowID);
        assertNotNull(result.data);
        assertEquals(tvShowResponse.getName(), result.data.getName());
    }

    @Test
    public void getMovieFavoritePaged() {
        DataSource.Factory<Integer, Movie> dataSourceFactory = mock(DataSource.Factory.class);

        when(localRepository.getMoviesFavoritePaged()).thenReturn(dataSourceFactory);
        repositoryData.getMovieFavoritePaged();
        Resource<PagedList<Movie>> result = Resource.success(PagedListUtil.mockPagedList(moviesResponse));

        verify(localRepository).getMoviesFavoritePaged();
        assertNotNull(result.data);
        assertEquals(moviesResponse.size(), result.data.size());
    }

    @Test
    public void getTVShowFavoritePaged() {
        DataSource.Factory<Integer, TVShow> dataSourceFactory = mock(DataSource.Factory.class);

        when(localRepository.getTVShowsFavoritePaged()).thenReturn(dataSourceFactory);
        repositoryData.getMovieFavoritePaged();
        Resource<PagedList<TVShow>> result = Resource.success(PagedListUtil.mockPagedList(tvshowsResponse));

        verify(localRepository).getTVShowsFavoritePaged();
        assertNotNull(result.data);
        assertEquals(tvshowsResponse.size(), result.data.size());
    }

}
