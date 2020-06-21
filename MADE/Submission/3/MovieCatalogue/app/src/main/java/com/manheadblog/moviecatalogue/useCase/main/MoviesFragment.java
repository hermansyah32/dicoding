package com.manheadblog.moviecatalogue.useCase.main;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.adapter.MoviesAdapter;
import com.manheadblog.moviecatalogue.model.Movie;
import com.manheadblog.moviecatalogue.useCase.moviedetail.MovieDetailActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment implements MoviesAdapter.OnItemClickCallaback, View.OnClickListener {

    private static final String PAGE = "PAGE";
    private static final String LIST_DATA = "LIST_DATA";

    private MovieFragmentViewModel viewModel;
    private MoviesAdapter moviesAdapter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private Button buttonLoad;
    private int page;

    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        page = 1;

        buttonLoad = view.findViewById(R.id.buttonLoad);
        recyclerView = view.findViewById(R.id.recyclerViewMovies);
        progressBar = view.findViewById(R.id.loadingBar);
        recyclerView.setHasFixedSize(true);

        viewModel = ViewModelProviders.of(this).get(MovieFragmentViewModel.class);
        viewModel.getMovies().observe(this, getMovies);

        if (savedInstanceState == null){
            showLoading(true);
            viewModel.setData(page);
            moviesAdapter = new MoviesAdapter(new ArrayList<Movie>());
        }else {
            showLoading(true);
            page = savedInstanceState.getInt(PAGE);
            moviesAdapter = new MoviesAdapter(savedInstanceState.<Movie>getParcelableArrayList(LIST_DATA));
        }

        buttonLoad.setOnClickListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(moviesAdapter);
        moviesAdapter.setOnItemClickCallaback(this);
    }

    private Observer<ArrayList<Movie>> getMovies = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> items) {
            if (items != null) {
                moviesAdapter.addItems(items);
                showLoading(false);
            }
        }
    };

    @Override
    public void OnItemClicked(Movie data) {
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.MOVIE_ID, data.getId());
        startActivity(intent);
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
            buttonLoad.setVisibility(View.GONE);
            recyclerView.setEnabled(false);
        } else {
            progressBar.setVisibility(View.GONE);
            buttonLoad.setVisibility(View.VISIBLE);
            recyclerView.setEnabled(false);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PAGE, page);
        outState.putParcelableArrayList(LIST_DATA, moviesAdapter.getMovieArrayList());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonLoad){
            page +=1;
            viewModel.setData(page);
            showLoading(true);
        }
    }
}
