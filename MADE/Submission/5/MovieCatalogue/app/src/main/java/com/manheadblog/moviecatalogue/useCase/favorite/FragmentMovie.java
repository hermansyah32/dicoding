package com.manheadblog.moviecatalogue.useCase.favorite;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.adapter.MoviesLocalAdapter;
import com.manheadblog.moviecatalogue.databinding.FragmentMovieFragmentBinding;
import com.manheadblog.moviecatalogue.db.MovieHelper;
import com.manheadblog.moviecatalogue.model.Movie;
import com.manheadblog.moviecatalogue.useCase.moviedetail.MovieDetailActivity;

import java.util.ArrayList;

import static com.manheadblog.moviecatalogue.db.DatabaseContract.MovieColumn.CONTENT_URI_MOVIE;

public class FragmentMovie extends Fragment implements MoviesLocalAdapter.OnItemClickCallaback {
    private static final String LIST_DATA = "LIST_DATA";

    private MoviesLocalAdapter moviesAdapter;
    private MovieHelper movieHelper;
    private FragmentMovieViewModel mViewModel;
    private FragmentMovieFragmentBinding binding;

    public FragmentMovie() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        movieHelper = MovieHelper.getInstance();

        mViewModel = ViewModelProviders.of(this).get(FragmentMovieViewModel.class);
        mViewModel.getMovies().observe(this, getMovies);
        binding.setViewModel(mViewModel);
        binding.setLifecycleOwner(this);

        binding.recyclerViewMovies.setHasFixedSize(true);
        if (savedInstanceState == null){
            moviesAdapter = new MoviesLocalAdapter(new ArrayList<Movie>());
            mViewModel.setData();
        }else {
            moviesAdapter = new MoviesLocalAdapter(savedInstanceState.<Movie>getParcelableArrayList(LIST_DATA));
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

    @Override
    public void OnItemLiked(Movie data, int position, View itemView) {
        ImageView imageView = itemView.findViewById(R.id.imageViewMovieFavorite);
        Uri uri = Uri.parse(CONTENT_URI_MOVIE + "/" + data.getId());
        getActivity().getContentResolver().delete(uri,null,null);
        showSnackbarMessage(getResources().getString(R.string.movie_disliked));
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp));
        ((FavoriteActivity) getActivity()).addRemovedMovie(data.getId());
        moviesAdapter.removeItem(position);
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
