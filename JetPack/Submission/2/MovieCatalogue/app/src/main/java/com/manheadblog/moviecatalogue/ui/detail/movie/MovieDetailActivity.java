package com.manheadblog.moviecatalogue.ui.detail.movie;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.databinding.ActivityMovieDetailBinding;
import com.manheadblog.moviecatalogue.entity.Movie;
import com.manheadblog.moviecatalogue.utils.ViewModelFactory;

import java.util.Objects;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String DATA = "data";

    private ActivityMovieDetailBinding binding;
    private MovieDetailActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        Movie movie = getIntent().getParcelableExtra(DATA);

        viewModel = obtainViewModel(this);

        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        viewModel.busy.set(true);
        viewModel.getData(movie.getId()).observe(this, success);
        binding.executePendingBindings();
    }

    private Observer<Movie> success = new Observer<Movie>() {
        @Override
        public void onChanged(Movie movie) {
            if (movie != null){
                viewModel.busy.set(false);
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

    @NonNull
    private static MovieDetailActivityViewModel obtainViewModel(AppCompatActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        return ViewModelProviders.of(activity, factory).get(MovieDetailActivityViewModel.class);
    }
}
