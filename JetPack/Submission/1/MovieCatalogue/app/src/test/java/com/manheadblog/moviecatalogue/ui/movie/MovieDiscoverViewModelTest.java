package com.manheadblog.moviecatalogue.ui.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.manheadblog.moviecatalogue.entity.Movie;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MovieDiscoverViewModelTest {

    private MovieDiscoverViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp(){
        viewModel = new MovieDiscoverViewModel();
    }

    @Test
    public void getData() {
        ArrayList<Movie> arrayList = viewModel.getData();
        assertNotNull(arrayList);
        assertEquals(12, arrayList.size());
    }
}