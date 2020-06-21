package com.manheadblog.moviecatalogue.useCase.main;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.adapter.ListviewMainAdapter;
import com.manheadblog.moviecatalogue.model.Movie;
import com.manheadblog.moviecatalogue.useCase.movie_detail.MovieDetailActivity;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {
    private String[] movieTitle, movieDescription, movieStatus, movieScore,
            movieCrews, movieReleaseInformation, movieOriginalLanguage, movieRuntime, movieBudget,
            movieRevenue, movieGenre;
    private TypedArray moviePoster;
    private ListviewMainAdapter listviewMainAdapter;
    private ArrayList<Movie> movies;

    private ListView listViewMain;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listViewMain = view.findViewById(R.id.listViewMain);
        listviewMainAdapter = new ListviewMainAdapter(getActivity());
        listViewMain.setAdapter(listviewMainAdapter);
        listViewMain.setOnItemClickListener(this);

        prepareData();
        addData();
    }

    private void prepareData() {
        movieTitle = getResources().getStringArray(R.array.movie_title);
        movieDescription = getResources().getStringArray(R.array.movie_description);
        movieStatus = getResources().getStringArray(R.array.movie_status);
        movieScore = getResources().getStringArray(R.array.movie_score);
        movieCrews = getResources().getStringArray(R.array.movie_crews);
        movieReleaseInformation = getResources().getStringArray(R.array.movie_release_information);
        movieOriginalLanguage = getResources().getStringArray(R.array.movie_original_language);
        movieRuntime = getResources().getStringArray(R.array.movie_runtime);
        movieBudget = getResources().getStringArray(R.array.movie_budget);
        movieRevenue = getResources().getStringArray(R.array.movie_revenue);
        movieGenre = getResources().getStringArray(R.array.movie_genre);
        moviePoster = getResources().obtainTypedArray(R.array.movie_poster);
    }

    private void addData() {
        movies = new ArrayList<>();

        for (int i = 0; i < movieTitle.length; i++) {
            Movie movie = new Movie();
            movie.setTitle(movieTitle[i]);
            movie.setDescription(movieDescription[i]);
            movie.setStatus(movieStatus[i]);
            movie.setScore(Integer.parseInt(movieScore[i]));
            movie.setCrews(movieCrews[i]);
            movie.setRelease_information(movieReleaseInformation[i]);
            movie.setOriginal_language(movieOriginalLanguage[i]);
            movie.setRuntime(movieRuntime[i]);
            movie.setBudget(Integer.parseInt(movieBudget[i]));
            movie.setRevenue(Integer.parseInt(movieRevenue[i]));
            movie.setGenre(movieGenre[i]);
            movie.setPoster(moviePoster.getResourceId(i, -1));
            movies.add(movie);
        }

        listviewMainAdapter.setMovies(movies);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Movie movie = (Movie) listviewMainAdapter.getItem(position);
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.MOVIE, movie);
        startActivity(intent);
    }
}
