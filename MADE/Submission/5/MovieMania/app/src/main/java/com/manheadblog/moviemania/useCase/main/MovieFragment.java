package com.manheadblog.moviemania.useCase.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.manheadblog.moviemania.R;
import com.manheadblog.moviemania.adapter.MoviesAdapter;
import com.manheadblog.moviemania.databinding.FragmentMovieBinding;
import com.manheadblog.moviemania.db.MovieHelper;
import com.manheadblog.moviemania.model.Movie;
import com.manheadblog.moviemania.useCase.moviedetail.MovieDetailActivity;

import java.util.ArrayList;

public class MovieFragment extends Fragment implements MoviesAdapter.OnItemClickCallaback {
    private static final String LIST_DATA = "LIST_DATA";

    private MoviesAdapter moviesAdapter;
    private MovieHelper movieHelper;
    private MovieFragmentViewModel mViewModel;
    private FragmentMovieBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        movieHelper = MovieHelper.getInstance();

        mViewModel = ViewModelProviders.of(this).get(MovieFragmentViewModel.class);
        mViewModel.getMovies().observe(this, getMovies);
        binding.setViewModel(mViewModel);
        binding.setLifecycleOwner(this);

        binding.recyclerViewMovies.setHasFixedSize(true);
        if (savedInstanceState == null){
            moviesAdapter = new MoviesAdapter(new ArrayList<Movie>());
            mViewModel.setData();
        }else {
            moviesAdapter = new MoviesAdapter(savedInstanceState.<Movie>getParcelableArrayList(LIST_DATA));
        }

        binding.recyclerViewMovies.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        binding.recyclerViewMovies.setNestedScrollingEnabled(false);
        binding.recyclerViewMovies.setAdapter(moviesAdapter);
        moviesAdapter.setOnItemClickCallaback(this);
    }

    private Observer<ArrayList<Movie>> getMovies = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> items) {
            if (items != null) {
                moviesAdapter.addItems(items);
                binding.recyclerViewMovies.setAdapter(moviesAdapter);
            }
        }
    };

    @Override
    public void OnItemClicked(Movie data) {
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.MOVIE_ID, data.getId());
        startActivity(intent);
    }

    private void showSnackbarMessage(String message) {
        Snackbar.make(binding.recyclerViewMovies, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(LIST_DATA, moviesAdapter.getMovieArrayList());
    }

}
