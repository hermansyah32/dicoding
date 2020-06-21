package com.manheadblog.moviecatalogue.ui.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.manheadblog.moviecatalogue.entity.TVShow;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TVShowDiscoverViewModelTest {

    private TVShowDiscoverViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp(){
        viewModel = new TVShowDiscoverViewModel();
    }

    @Test
    public void getData() {
        ArrayList<TVShow> arrayList = viewModel.getData();
        assertNotNull(arrayList);
        assertEquals(12, arrayList.size());
    }
}