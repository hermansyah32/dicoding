package com.manheadblog.moviecatalogue.ui.detail.tvshow;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.databinding.ActivityTvshowDetailBinding;
import com.manheadblog.moviecatalogue.entity.TVShow;

import java.util.Objects;

public class TVShowDetailActivity extends AppCompatActivity {

    public static final String DATA = "data";

    private ActivityTvshowDetailBinding binding;
    private TVShowDetailActivityViewModel viewModel;
    private TVShow tvShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_tvshow_detail);
        tvShow = getIntent().getParcelableExtra(DATA);

        viewModel = ViewModelProviders.of(this).get(TVShowDetailActivityViewModel.class);
        viewModel.getSuccess().observe(this, success);

        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        viewModel.getData(tvShow.getId());
        binding.executePendingBindings();
    }

    private Observer<TVShow> success = new Observer<TVShow>() {
        @Override
        public void onChanged(TVShow tvShow) {
            if (tvShow != null){
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
}
