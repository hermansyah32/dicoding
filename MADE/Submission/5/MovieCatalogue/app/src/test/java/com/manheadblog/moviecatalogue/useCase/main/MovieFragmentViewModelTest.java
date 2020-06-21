package com.manheadblog.moviecatalogue.useCase.main;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class MovieFragmentViewModelTest {
    @Mock
    MovieFragmentViewModel viewModel;

    @Before
    public void setUp(){
        viewModel = mock(MovieFragmentViewModel.class);
    }

    @Test
    public void testData() {
        int current_page = 1;
        viewModel.setData(current_page);
        System.out.println("testResult " + String.valueOf(viewModel.getMovies().getValue().size()));
    }
}