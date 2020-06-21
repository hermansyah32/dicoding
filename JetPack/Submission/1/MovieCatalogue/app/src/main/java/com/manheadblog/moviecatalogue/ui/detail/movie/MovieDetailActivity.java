package com.manheadblog.moviecatalogue.ui.detail.movie;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.databinding.ActivityMovieDetailBinding;
import com.manheadblog.moviecatalogue.entity.Movie;

import java.util.Objects;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String DATA = "data";

    private ActivityMovieDetailBinding binding;
    private MovieDetailActivityViewModel viewModel;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        movie = getIntent().getParcelableExtra(DATA);

        viewModel = ViewModelProviders.of(this).get(MovieDetailActivityViewModel.class);
        viewModel.getSuccess().observe(this, success);

        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        viewModel.getData(movie.getId());
        binding.executePendingBindings();
    }

    private Observer<Movie> success = new Observer<Movie>() {
        @Override
        public void onChanged(Movie movie) {
            if (movie != null){
                binding.setModel(movie);
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
