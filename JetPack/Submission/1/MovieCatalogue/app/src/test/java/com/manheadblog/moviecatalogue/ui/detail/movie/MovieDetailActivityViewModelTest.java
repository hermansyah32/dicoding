package com.manheadblog.moviecatalogue.ui.detail.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.manheadblog.moviecatalogue.entity.Movie;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MovieDetailActivityViewModelTest {

    private MovieDetailActivityViewModel viewModel;
    private String dummyTitle = "Aquaman";
    private int dummyID = 1;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        viewModel = new MovieDetailActivityViewModel();
    }

    @Test
    public void getData() {
        Movie movie = viewModel.getData(dummyID);

        assertNotNull(movie);
        assertEquals(movie.getTitle(), dummyTitle);
    }
}