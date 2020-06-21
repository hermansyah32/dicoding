package com.manheadblog.moviecatalogue.useCase.release;

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
import com.manheadblog.moviecatalogue.adapter.MoviesAdapter;
import com.manheadblog.moviecatalogue.databinding.ReleaseMovieFragmentBinding;
import com.manheadblog.moviecatalogue.db.MovieHelper;
import com.manheadblog.moviecatalogue.helper.MovieMappingHelper;
import com.manheadblog.moviecatalogue.model.Movie;
import com.manheadblog.moviecatalogue.useCase.moviedetail.MovieDetailActivity;

import java.util.ArrayList;

import static com.manheadblog.moviecatalogue.db.DatabaseContract.MovieColumn.CONTENT_URI_MOVIE;

public class ReleaseMovieFragment extends Fragment implements MoviesAdapter.OnItemClickCallaback, View.OnClickListener {

    private static final String PAGE = "PAGE";
    private static final String TOTAL_PAGE = "TOTAL_PAGE";
    private static final String LIST_DATA = "LIST_DATA";
    private static final String LIST_DATA_LOCAL = "LIST_DATA_LOCAL";

    private MoviesAdapter moviesAdapter;
    private MovieHelper movieHelper;
    private ReleaseMovieViewModel viewModel;
    private ReleaseMovieFragmentBinding binding;

    public ReleaseMovieFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.release_movie_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.recyclerViewMovies.setHasFixedSize(true);
        movieHelper = MovieHelper.getInstance();
        viewModel = ViewModelProviders.of(this).get(ReleaseMovieViewModel.class);
        viewModel.getMovies().observe(this, getMovies);

        if (savedInstanceState == null){
            moviesAdapter = new MoviesAdapter(new ArrayList<Movie>(), new ArrayList<Movie>());
            showLoading(true);
            viewModel.setData(1);
        }else {
            viewModel.page = savedInstanceState.getInt(PAGE);
            viewModel.page = savedInstanceState.getInt(TOTAL_PAGE);
            moviesAdapter = new MoviesAdapter(savedInstanceState.<Movie>getParcelableArrayList(LIST_DATA),
                    savedInstanceState.<Movie>getParcelableArrayList(LIST_DATA_LOCAL));
        }

        binding.buttonLoad.setOnClickListener(this);
        binding.recyclerViewMovies.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        binding.recyclerViewMovies.setNestedScrollingEnabled(false);
        binding.recyclerViewMovies.setAdapter(moviesAdapter);
        moviesAdapter.setOnItemClickCallaback(this);
        showLoading(true);
    }

    private Observer<ArrayList<Movie>> getMovies = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> items) {
            if (items != null) {
                showLoading(false);
                moviesAdapter.addItems(items);
            }
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            binding.loadingBar.setVisibility(View.VISIBLE);
            binding.buttonLoad.setVisibility(View.GONE);
            binding.recyclerViewMovies.setEnabled(false);
        } else {
            binding.loadingBar.setVisibility(View.GONE);
            binding.buttonLoad.setVisibility(View.VISIBLE);
            binding.recyclerViewMovies.setEnabled(false);
        }
    }

    public void refreshList(){
        moviesAdapter.clearData();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonLoad){
            viewModel.setData(viewModel.page);
            showLoading(true);
        }
    }

    @Override
    public void OnItemClicked(Movie data) {
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.MOVIE_ID, data.getId());
        startActivity(intent);
    }

    @Override
    public void OnItemLiked(boolean isLiked, Movie data, int position, View itemView) {
        ImageView imageView = itemView.findViewById(R.id.imageViewMovieFavorite);
        if (!isLiked){
            getContext().getContentResolver().insert(CONTENT_URI_MOVIE, MovieMappingHelper.mapObjectToContentValues(data));
            showSnackbarMessage(getResources().getString(R.string.movie_liked));
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_black_24dp));
            moviesAdapter.addItemLocal(data, position);
        }else {
            Uri uri = Uri.parse(CONTENT_URI_MOVIE + "/" + data.getId());
            getContext().getContentResolver().delete(uri,null,null);
            showSnackbarMessage(getResources().getString(R.string.movie_disliked));
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp));
            moviesAdapter.removeItemLocal(position);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PAGE, viewModel.page);
        outState.putInt(TOTAL_PAGE, viewModel.total_pages);
        outState.putParcelableArrayList(LIST_DATA, moviesAdapter.getMovieArrayList());
        outState.putParcelableArrayList(LIST_DATA_LOCAL, moviesAdapter.getLocalMovieArrayList());
    }

    private void showSnackbarMessage(String message) {
        Snackbar.make(binding.recyclerViewMovies, message, Snackbar.LENGTH_SHORT).show();
    }

}
