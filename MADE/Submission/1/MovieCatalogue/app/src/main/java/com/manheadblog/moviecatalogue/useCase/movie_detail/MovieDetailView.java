package com.manheadblog.moviecatalogue.useCase.movie_detail;

import com.manheadblog.moviecatalogue.model.Movie;

public interface MovieDetailView {
    void showMovie(Movie movie);

    void showReleaseInformation(String[][] data);

    void showFeaturedCrew(String[][] crew);
}
