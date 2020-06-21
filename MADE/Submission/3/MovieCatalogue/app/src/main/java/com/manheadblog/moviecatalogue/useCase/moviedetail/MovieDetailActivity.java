package com.manheadblog.moviecatalogue.useCase.moviedetail;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.model.GenresItem;
import com.manheadblog.moviecatalogue.model.Movie;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String MOVIE_ID = "MOVIE_ID";
    public static final String MOVIE_DATA = "MOVIE_DATA";
    private static final String TAG = "MovieDetailActivity";

    private Movie movie;
    private int movie_id;
    private TextView textViewTitle, textViewDescription, textViewStatus, textViewLanguage,
            textViewBudget, textViewRevenue, textViewLenght, textViewScore, textViewRelease;
    private LinearLayout layoutGenre;
    private ConstraintLayout constraintLayout1, constraintLayout2;
    private ImageView imageViewBackground, imageViewPoster;
    private MovieDetailViewModel viewModel;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        movie_id = getIntent().getIntExtra(MOVIE_ID, 1);

        textViewTitle = findViewById(R.id.textViewMovieTitle);
        textViewDescription = findViewById(R.id.textViewMovieDescription);
        textViewStatus = findViewById(R.id.textViewMovieStatus);
        textViewLanguage = findViewById(R.id.textViewMovieLanguage);
        textViewBudget = findViewById(R.id.textViewMovieBudget);
        textViewRevenue = findViewById(R.id.textViewMovieRevenue);
        textViewLenght = findViewById(R.id.textViewMovieLenght);
        textViewScore = findViewById(R.id.textViewMovieScore);
        textViewRelease = findViewById(R.id.textViewMovieRelease);
        imageViewBackground = findViewById(R.id.imageViewMovieBackground);
        imageViewPoster = findViewById(R.id.imageViewMoviePoster);
        progressBar = findViewById(R.id.loadingBar);
        constraintLayout1 = findViewById(R.id.constraintLayout2);
        constraintLayout2 = findViewById(R.id.constraintLayout3);
        layoutGenre = findViewById(R.id.layoutGenre);

        viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);
        viewModel.getData().observe(this, getMovies);

        if (savedInstanceState == null){
            showLoading(true);
            viewModel.setData(movie_id);
        }else {
            movie_id= savedInstanceState.getInt(MOVIE_ID, 1);
            movie = savedInstanceState.getParcelable(MOVIE_DATA);
            showMovie();
        }
    }

    private Observer<Movie> getMovies = new Observer<Movie>() {
        @Override
        public void onChanged(Movie item) {
            if (item != null) {
                movie = item;
                showMovie();
                showLoading(false);
            }
        }
    };

    private void showMovie() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.CANADA);
        String budget = numberFormat.format(movie.getBudget());
        String revenue = numberFormat.format(movie.getRevenue());
        if (movie.getBudget() < 1) budget = "-";
        if (movie.getRevenue() < 1) revenue = "-";

        showGenre();

        Glide.with(this).load(movie.getPosterPath()).into(imageViewPoster);
        Glide.with(this).load(movie.getBackdropPath())
                .apply(bitmapTransform(new BlurTransformation(5)))
                .into(imageViewBackground);

        textViewTitle.setText(movie.getTitle());
        textViewDescription.setText(movie.getOverview());
        textViewStatus.setText(movie.getStatus());
        textViewLanguage.setText(new Locale(movie.getOriginalLanguage()).getDisplayLanguage());
        textViewLenght.setText(getRuntime());
        textViewBudget.setText(budget);
        textViewRevenue.setText(revenue);
        textViewRelease.setText(movie.getReleaseDate());
        setScore();
    }


    private void setScore() {
        textViewScore.setText(String.valueOf(movie.getVoteAverage()));
        if (movie.getVoteAverage() >= 7) {
            textViewScore.setBackgroundColor(getResources().getColor(R.color.score_green));
        } else if (movie.getVoteAverage() < 7 && movie.getVoteAverage() > 5) {
            textViewScore.setBackgroundColor(getResources().getColor(R.color.score_yellow));
        } else {
            textViewScore.setBackgroundColor(getResources().getColor(R.color.score_red));
        }
    }

    private String getRuntime() {
        String result = "";
        int hour = movie.getRuntime() / 60;
        int minute = movie.getRuntime() % 60;
        if (movie.getRuntime()<1){
            result = "";
        }else if (movie.getRuntime()<60){
            result = minute +
                    getResources().getString(R.string.minute);
        }else {
            result = hour +
                    getResources().getString(R.string.hour) + " " +
                    minute +
                    getResources().getString(R.string.minute);
        }
        return result;
    }

    private void showGenre() {
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lparams.setMargins(16, 0, 0, 0
        );
        for (GenresItem genre : movie.getGenres()) {
            TextView textView = new TextView(this);
            textView.setLayoutParams(lparams);
            textView.setText(genre.getName());
            textView.setBackground(getResources().getDrawable(R.drawable.genre_textview));
            textView.setTextColor(getResources().getColor(R.color.dark_text_subtitle));
            this.layoutGenre.addView(textView);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
            constraintLayout1.setVisibility(View.GONE);
            constraintLayout2.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            constraintLayout1.setVisibility(View.VISIBLE);
            constraintLayout2.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.getInt(MOVIE_ID, movie_id);
        outState.putParcelable(MOVIE_DATA, movie);
    }
}
