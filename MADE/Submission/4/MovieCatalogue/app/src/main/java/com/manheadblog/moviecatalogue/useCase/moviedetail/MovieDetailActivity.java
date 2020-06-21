package com.manheadblog.moviecatalogue.useCase.moviedetail;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.databinding.ActivityMovieDetailBinding;
import com.manheadblog.moviecatalogue.model.GenresItem;
import com.manheadblog.moviecatalogue.model.Movie;

import java.util.Objects;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String MOVIE_ID = "MOVIE_ID";
    private static final String TAG = "MovieDetailActivity";

    private Movie movie;
    private int movie_id;
    private MovieDetailViewModel viewModel;

    ActivityMovieDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        movie_id = getIntent().getIntExtra(MOVIE_ID, 1);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);

        viewModel.getData().observe(this, getMovies);

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        if (savedInstanceState == null){
            viewModel.setData(movie_id);
        }else {
            movie_id= savedInstanceState.getInt(MOVIE_ID, 1);
            showMovie();
        }
    }

    private Observer<Movie> getMovies = new Observer<Movie>() {
        @Override
        public void onChanged(Movie item) {
            if (item != null) {
                movie = item;
                showMovie();
                binding.setMovieModel(movie);
            }
        }
    };

    private void showMovie() {
        showGenre();

        Glide.with(this).load(movie.getURLPosterPath()).into(binding.imageViewMoviePoster);
        Glide.with(this).load(movie.getURLBackdropPath())
                .apply(bitmapTransform(new BlurTransformation(5)))
                .into(binding.imageViewMovieBackground);
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
            this.binding.layoutGenre.addView(textView);
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(MOVIE_ID, movie_id);
    }
}
