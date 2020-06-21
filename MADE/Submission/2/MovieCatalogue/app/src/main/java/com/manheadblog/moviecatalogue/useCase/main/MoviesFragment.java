package com.manheadblog.moviecatalogue.useCase.main;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
public class MoviesFragment extends Fragment implements MoviesAdapter.OnItemClickCallaback {

    private String[] movieTitle, movieDescription, movieStatus, movieScore,
            movieReleaseInformation, movieOriginalLanguage, movieRuntime, movieBudget,
            movieRevenue, movieGenre;
    private TypedArray moviePoster;
    private ArrayList<Movie> movies;

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

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewMovies);
        recyclerView.setHasFixedSize(true);
        prepareData();
        addData();

        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        MoviesAdapter moviesAdapter = new MoviesAdapter(movies);
        recyclerView.setAdapter(moviesAdapter);
        moviesAdapter.setOnItemClickCallaback(this);
    }

    private void prepareData() {
        movieTitle = getResources().getStringArray(R.array.movies_title);
        movieDescription = getResources().getStringArray(R.array.movies_description);
        movieStatus = getResources().getStringArray(R.array.movies_status);
        movieScore = getResources().getStringArray(R.array.movies_score);
        movieReleaseInformation = getResources().getStringArray(R.array.movies_release_information);
        movieOriginalLanguage = getResources().getStringArray(R.array.movies_language);
        movieRuntime = getResources().getStringArray(R.array.movies_runtime);
        movieBudget = getResources().getStringArray(R.array.movies_budget);
        movieRevenue = getResources().getStringArray(R.array.movies_revenue);
        movieGenre = getResources().getStringArray(R.array.movies_genre);
        moviePoster = getResources().obtainTypedArray(R.array.movies_drawable);
    }

    private void addData() {
        movies = new ArrayList<>();

        for (int i = 0; i < movieTitle.length; i++) {
            Movie movie = new Movie();
            movie.setTitle(movieTitle[i]);
            movie.setDescription(movieDescription[i]);
            movie.setStatus(movieStatus[i]);
            movie.setScore(Integer.parseInt(movieScore[i]));
            movie.setRelease_information(movieReleaseInformation[i]);
            movie.setOriginal_language(movieOriginalLanguage[i]);
            movie.setRuntime(movieRuntime[i]);
            movie.setBudget(Integer.parseInt(movieBudget[i]));
            movie.setRevenue(Integer.parseInt(movieRevenue[i]));
            movie.setGenre(movieGenre[i]);
            movie.setPoster(moviePoster.getResourceId(i, -1));
            movies.add(movie);
        }
        moviePoster.recycle();
    }

    @Override
    public void OnItemClicked(Movie data) {
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.MOVIE_DATA, data);
        startActivity(intent);
    }

}
