package com.manheadblog.moviecatalogue.ui.detail.tvshow;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.databinding.ActivityTvshowDetailBinding;
import com.manheadblog.moviecatalogue.entity.TVShow;
import com.manheadblog.moviecatalogue.utils.ViewModelFactory;

import java.util.Objects;

public class TVShowDetailActivity extends AppCompatActivity {

    public static final String DATA = "data";

    private ActivityTvshowDetailBinding binding;
    private TVShowDetailActivityViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_tvshow_detail);
        TVShow tvShow = getIntent().getParcelableExtra(DATA);

        viewModel = obtainViewModel(this);

        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        viewModel.busy.set(true);
        viewModel.getData(tvShow.getId()).observe(this, success);
        binding.executePendingBindings();
    }

    private Observer<TVShow> success = new Observer<TVShow>() {
        @Override
        public void onChanged(TVShow tvShow) {
            if (tvShow != null){
                viewModel.busy.set(false);
                binding.setModel(tvShow);
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
    private static TVShowDetailActivityViewModel obtainViewModel(AppCompatActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        return ViewModelProviders.of(activity, factory).get(TVShowDetailActivityViewModel.class);
    }
}
