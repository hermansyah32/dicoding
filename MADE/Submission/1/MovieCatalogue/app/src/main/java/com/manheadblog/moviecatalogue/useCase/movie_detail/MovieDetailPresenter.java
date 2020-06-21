package com.manheadblog.moviecatalogue.useCase.movie_detail;

import com.manheadblog.moviecatalogue.model.Movie;

public class MovieDetailPresenter {
    private MovieDetailView movieDetailView;
    private Movie movie;

    public MovieDetailPresenter(MovieDetailView movieDetailView, Movie movie) {
        this.movieDetailView = movieDetailView;
        this.movie = movie;
        this.movieDetailView.showMovie(this.movie);
        getReleaseinformation();
        getFeaturedCrew();
    }

    /**
     * @result[movieIndex][item] where @movieIndex as movie index position
     * where @item  0 => release mode
     * 1 => release date
     * 2 => release plase
     */
    public void getReleaseinformation() {
        String[][] result;
        String[] resultData = movie.getRelease_information().split(";");
        result = new String[resultData.length][3];
        for (int i = 0; i < resultData.length; i++) {
            String[] item = resultData[i].split(":");
            result[i][0] = item[0];
            result[i][1] = item[1];
            result[i][2] = item[2];
        }
        movieDetailView.showReleaseInformation(result);
    }

    /**
     * @result[movieIndex][item] where @movieIndex as movie index position
     * where @item  0 => crew name
     * 1 => crew position
     */
    public void getFeaturedCrew() {
        String[][] result;
        String[] resultData = movie.getCrews().split(";");
        result = new String[resultData.length][2];
        for (int i = 0; i < resultData.length; i++) {
            String[] item = resultData[i].split(":");
            result[i][0] = item[0];
            result[i][1] = item[1];
        }
        movieDetailView.showFeaturedCrew(result);
    }

}
