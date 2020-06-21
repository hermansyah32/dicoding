package com.manheadblog.moviecatalogue.ui.detail.movie;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.data.resource.Resource;
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
        viewModel.setId(movie.getId());

        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        viewModel.movie.observe(this, success);
        binding.executePendingBindings();
    }

    private Observer<Resource<Movie>> success = new Observer<Resource<Movie>>() {
        @Override
        public void onChanged(Resource<Movie> response) {
            if (response != null){
                switch (response.status){
                    case LOADING:
                        viewModel.busy.set(true);
                        break;
                    case SUCCESS:
                        if (response.data != null){
                            binding.setModel(response.data);
                        }
                        viewModel.busy.set(false);
                        break;
                    case ERROR:
                        viewModel.busy.set(false);
                        break;
                }
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
