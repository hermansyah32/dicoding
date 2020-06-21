package com.manheadblog.moviecatalogue.useCase.main;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.adapter.MoviesAdapter;
import com.manheadblog.moviecatalogue.db.MovieHelper;
import com.manheadblog.moviecatalogue.model.Movie;
import com.manheadblog.moviecatalogue.useCase.favorite.FavoriteActivity;
import com.manheadblog.moviecatalogue.useCase.moviedetail.MovieDetailActivity;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment implements MoviesAdapter.OnItemClickCallaback, View.OnClickListener {

    private static final String PAGE = "PAGE";
    private static final String LIST_DATA = "LIST_DATA";
    private static final String LIST_DATA_LOCAL = "LIST_DATA_LOCAL";

    private MovieFragmentViewModel viewModel;
    private MoviesAdapter moviesAdapter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private Button buttonLoad;
    private int page;
    private MovieHelper movieHelper;

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

        movieHelper = MovieHelper.getInstance();

        viewModel = ViewModelProviders.of(this).get(MovieFragmentViewModel.class);
        viewModel.getMovies().observe(this, getMovies);
        viewModel.getLocalMovies().observe(this, getLocalMovies);

        if (savedInstanceState == null){
            showLoading(true);
            viewModel.setData(page);
            moviesAdapter = new MoviesAdapter(new ArrayList<Movie>(), new ArrayList<Movie>());
        }else {
            showLoading(true);
            page = savedInstanceState.getInt(PAGE);
            moviesAdapter = new MoviesAdapter(savedInstanceState.<Movie>getParcelableArrayList(LIST_DATA),
                    savedInstanceState.<Movie>getParcelableArrayList(LIST_DATA_LOCAL));
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

    private Observer<ArrayList<Movie>> getLocalMovies = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> movies) {
            if (movies != null){
                moviesAdapter.addItemsLocal(movies);
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
    public void OnItemLiked(boolean isLiked, Movie data, int position, View itemView) {
        //Item liked, add to local database
        ImageView imageView = itemView.findViewById(R.id.imageViewMovieFavorite);
        if (!isLiked){
            long result = movieHelper.insert(data);
            if (result>0){
                showSnackbarMessage(getResources().getString(R.string.movie_liked));
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_black_24dp));
                data.setDatabase_id(result);
                moviesAdapter.addItemLocal(data, position);
            }else {
                showSnackbarMessage(getResources().getString(R.string.movie_failed_liked));
            }
        }else {
            long result = movieHelper.delete(data.getDatabase_id());
            if (result>0){
                showSnackbarMessage(getResources().getString(R.string.movie_disliked));
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp));
                moviesAdapter.removeItemLocal(position);
            }else {
                showSnackbarMessage(getResources().getString(R.string.movie_failed_disliked));
            }
        }
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
        outState.putParcelableArrayList(LIST_DATA_LOCAL, moviesAdapter.getLocalMovieArrayList());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonLoad){
            page +=1;
            viewModel.setData(page);
            showLoading(true);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null){
            if (requestCode == MainActivity.MainActivityResult){
                if (resultCode == RESULT_OK){
                    //Refresh local database disini
                    ArrayList<Integer> removedMovie = data.getIntegerArrayListExtra(FavoriteActivity.REMOVED_MOVIE);
                    for (int i = 0; i < removedMovie.size(); i++) {
                        moviesAdapter.removeItemLocalbyId(removedMovie.get(i));
                    }
                }
            }
        }
    }

    private void showSnackbarMessage(String message) {
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_SHORT).show();
    }
}
