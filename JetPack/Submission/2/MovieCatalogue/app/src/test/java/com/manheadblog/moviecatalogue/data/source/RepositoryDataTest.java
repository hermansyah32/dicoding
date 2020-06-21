package com.manheadblog.moviecatalogue.data.source;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.manheadblog.moviecatalogue.data.remote.MoviePagingResponse;
import com.manheadblog.moviecatalogue.data.remote.RemoteRepository;
import com.manheadblog.moviecatalogue.data.remote.TVShowPagingResponse;
import com.manheadblog.moviecatalogue.entity.Movie;
import com.manheadblog.moviecatalogue.entity.TVShow;
import com.manheadblog.moviecatalogue.utils.FakeDataDummy;
import com.manheadblog.moviecatalogue.utils.LiveDataTestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RepositoryDataTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteRepository remoteRepository = Mockito.mock(RemoteRepository.class);
    private Application application = Mockito.mock(Application.class);
    private FakeRepositoryData repositoryData = new FakeRepositoryData(remoteRepository);

    private int dummyPage = 1;
    private MoviePagingResponse moviePagingResponse = FakeDataDummy.generateMovies(getClass().getClassLoader());
    private TVShowPagingResponse tvShowPagingResponse = FakeDataDummy.generateTVShows(getClass().getClassLoader());
    private Movie movieResponse = moviePagingResponse.getResults().get(0);
    private TVShow tvShowResponse = tvShowPagingResponse.getResults().get(0);

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void getMovieDiscover(){
        doAnswer(invocation -> {
            ((RemoteRepository.LoadMoviesDiscoverCallback)invocation.getArguments()[1]).onAllMoviesReceived(moviePagingResponse);
            return null;
        }).when(remoteRepository).getMoviesDiscover(eq(dummyPage), any(RemoteRepository.LoadMoviesDiscoverCallback.class));

        MoviePagingResponse result = LiveDataTestUtil.getValue(repositoryData.getMovieDiscover(dummyPage));
        verify(remoteRepository, times(1)).getMoviesDiscover(eq(dummyPage),any(RemoteRepository.LoadMoviesDiscoverCallback.class));

        assertNotNull(result);
        assertEquals(moviePagingResponse.getResults().size(), result.getResults().size());
    }

    @Test
    public void getTVShowDiscover(){
        doAnswer(invocation -> {
            ((RemoteRepository.LoadTVShowsDiscoverCallback)invocation.getArguments()[1]).onAllTVShowsReceived(tvShowPagingResponse);
            return null;
        }).when(remoteRepository).getTVShowsDiscover(eq(dummyPage), any(RemoteRepository.LoadTVShowsDiscoverCallback.class));

        TVShowPagingResponse result = LiveDataTestUtil.getValue(repositoryData.getTVShowDiscover(dummyPage));

        verify(remoteRepository, times(1)).getTVShowsDiscover(eq(dummyPage),any(RemoteRepository.LoadTVShowsDiscoverCallback.class));

        assertNotNull(result);
        assertEquals(tvShowPagingResponse.getResults().size(), result.getResults().size());
    }

    @Test
    public void getMovieDetail(){

        doAnswer(invocation -> {
            ((RemoteRepository.LoadMovieDetailCallback)invocation.getArguments()[1]).onMovieDetailReceived(movieResponse);
            return null;
        }).when(remoteRepository).getMovieDetail(eq(movieResponse.getId()), any(RemoteRepository.LoadMovieDetailCallback.class));

        Movie result = LiveDataTestUtil.getValue(repositoryData.getMovie(movieResponse.getId()));

        verify(remoteRepository, times(1)).getMovieDetail(eq(movieResponse.getId()),any(RemoteRepository.LoadMovieDetailCallback.class));

        assertNotNull(result);
        assertEquals(movieResponse.getTitle(), result.getTitle());
    }

    @Test
    public void getTVShowDetail(){
        doAnswer(invocation -> {
            ((RemoteRepository.LoadTVShowDetailCallback)invocation.getArguments()[1]).onTVShowDetailReceived(tvShowResponse);
            return null;
        }).when(remoteRepository).getTVShowDetail(eq(tvShowResponse.getId()), any(RemoteRepository.LoadTVShowDetailCallback.class));

        TVShow result = LiveDataTestUtil.getValue(repositoryData.getTVShow(tvShowResponse.getId()));

        verify(remoteRepository, times(1)).getTVShowDetail(eq(tvShowResponse.getId()),any(RemoteRepository.LoadTVShowDetailCallback.class));

        assertNotNull(result);
        assertEquals(tvShowResponse.getName(), result.getName());
    }
}
