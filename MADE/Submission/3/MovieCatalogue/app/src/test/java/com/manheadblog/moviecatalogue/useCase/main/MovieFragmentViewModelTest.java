package com.manheadblog.moviecatalogue.useCase.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.manheadblog.moviecatalogue.Utils.GsonUtils;
import com.manheadblog.moviecatalogue.api.API;
import com.manheadblog.moviecatalogue.model.Movie;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static org.junit.Assert.*;
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