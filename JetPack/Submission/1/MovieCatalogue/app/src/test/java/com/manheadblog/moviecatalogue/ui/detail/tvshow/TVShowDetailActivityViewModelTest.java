package com.manheadblog.moviecatalogue.ui.detail.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.manheadblog.moviecatalogue.entity.TVShow;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TVShowDetailActivityViewModelTest {

    private TVShowDetailActivityViewModel viewModel;
    private String dummyTitle = "Arrow";
    private int dummyID = 1;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        viewModel = new TVShowDetailActivityViewModel();
    }

    @Test
    public void getData() {
        TVShow tvShow = viewModel.getData(dummyID);

        assertNotNull(tvShow);
        assertEquals(tvShow.getTitle(), dummyTitle);
    }
}