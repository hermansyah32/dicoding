package com.manheadblog.moviecatalogue.useCase.tvdetail;

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
import com.manheadblog.moviecatalogue.databinding.ActivityTvshowDetailBinding;
import com.manheadblog.moviecatalogue.model.GenresItem;
import com.manheadblog.moviecatalogue.model.TVShow;

import java.util.Objects;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class TVShowDetailActivity extends AppCompatActivity {

    public static final String TVSHOW_ID = "TVSHOW_ID";

    private TVShow tvShow;
    private int tvshow_id;
    private TVShowDetailViewModel viewModel;

    ActivityTvshowDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tvshow_id = getIntent().getIntExtra(TVSHOW_ID, 1);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_tvshow_detail);

        viewModel = ViewModelProviders.of(this).get(TVShowDetailViewModel.class);

        viewModel.getData().observe(this, getMovies);

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        if (savedInstanceState == null){
            viewModel.setData(tvshow_id);
        }else {
            tvshow_id = savedInstanceState.getInt(TVSHOW_ID, 1);
            showTVShow();
        }
    }

    private Observer<TVShow> getMovies = new Observer<TVShow>() {
        @Override
        public void onChanged(TVShow item) {
            if (item != null) {
                tvShow = item;
                showTVShow();
                binding.setTvshowModel(item);
            }
        }
    };

    private void showTVShow() {
        showGenre();
        Glide.with(this).load(tvShow.getURLPosterPath()).into(binding.imageViewTVShowPoster);
        Glide.with(this).load(tvShow.getURLBackdropPath())
                .apply(bitmapTransform(new BlurTransformation(5)))
                .into(binding.imageViewTVShowBackground);
    }

    private void showGenre() {
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lparams.setMargins(16, 0, 0, 0
        );
        for (GenresItem genre : tvShow.getGenres()) {
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
        outState.putInt(TVSHOW_ID, tvshow_id);
    }
}
