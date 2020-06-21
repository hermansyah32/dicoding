package com.manheadblog.moviecatalogue.useCase.tvdetail;

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
import com.manheadblog.moviecatalogue.model.TVShow;

import java.util.Locale;
import java.util.Objects;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class TVShowDetailActivity extends AppCompatActivity {

    public static final String TVSHOW_ID = "TVSHOW_ID";
    public static final String TVSHOW_DATA = "TVSHOW_DATA";

    private TVShow tvShow;
    private int tvshow_id;
    private TextView textViewTItle, textViewDescription, textViewStatus, textViewLanguage, textViewRelease,
            textViewRuntime, textViewScore, textViewFirstRelease, textViewLastRelease, textViewSeason,
            textViewEpisode;
    private ImageView imageViewBackground, imageViewPoster;
    private TVShowDetailViewModel viewModel;
    private LinearLayout layoutGenre;
    private ConstraintLayout constraintLayout1, constraintLayout2;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshow_detail);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvshow_id = getIntent().getIntExtra(TVSHOW_ID, 1);

        textViewTItle = findViewById(R.id.textViewTVShowTitle);
        textViewDescription = findViewById(R.id.textViewTVShowDescription);
        textViewStatus = findViewById(R.id.textViewTVShowStatus);
        textViewRelease = findViewById(R.id.textViewTVShowReleaseDate);
        textViewLanguage = findViewById(R.id.textViewTVShowLanguage);
        textViewScore = findViewById(R.id.textViewTVShowScore);
        textViewRuntime = findViewById(R.id.textViewTVShowRuntime);
        textViewFirstRelease = findViewById(R.id.textViewTvShowFirstRelease);
        textViewLastRelease = findViewById(R.id.textViewTVShowLastRelease);
        textViewSeason = findViewById(R.id.textViewNumberOfSeasons);
        textViewEpisode = findViewById(R.id.textViewNumberOfEpisodes);
        imageViewBackground = findViewById(R.id.imageViewTVShowBackground);
        imageViewPoster = findViewById(R.id.imageViewTVShowPoster);
        progressBar = findViewById(R.id.loadingBar);
        constraintLayout1 = findViewById(R.id.constraintLayout2);
        constraintLayout2 = findViewById(R.id.constraintLayout3);
        layoutGenre = findViewById(R.id.layoutGenre);

        viewModel = ViewModelProviders.of(this).get(TVShowDetailViewModel.class);
        viewModel.getData().observe(this, getMovies);

        if (savedInstanceState == null){
            showLoading(true);
            viewModel.setData(tvshow_id);
        }else {
            tvshow_id = savedInstanceState.getInt(TVSHOW_ID, 1);
            tvShow = savedInstanceState.getParcelable(TVSHOW_DATA);
            showTVShow();
        }
    }

    private Observer<TVShow> getMovies = new Observer<TVShow>() {
        @Override
        public void onChanged(TVShow item) {
            if (item != null) {
                tvShow = item;
                showTVShow();
                showLoading(false);
            }
        }
    };

    private void showTVShow() {
        textViewTItle.setText(tvShow.getName());
        textViewDescription.setText(tvShow.getOverview());
        textViewStatus.setText(tvShow.getStatus());
        textViewLanguage.setText(new Locale(tvShow.getOriginalLanguage()).getDisplayLanguage());
        textViewRuntime.setText(getRuntime());
        textViewRelease.setText(tvShow.getFirstAirDate());
        textViewFirstRelease.setText(tvShow.getFirstAirDate());
        textViewLastRelease.setText(tvShow.getLastAirDate());
        textViewSeason.setText(String.valueOf(tvShow.getNumberOfSeasons()));
        textViewEpisode.setText(String.valueOf(tvShow.getNumberOfEpisodes()));
        setScore();
        showGenre();
        Glide.with(this).load(tvShow.getPosterPath()).into(imageViewPoster);
        Glide.with(this).load(tvShow.getBackdropPath())
                .apply(bitmapTransform(new BlurTransformation(5)))
                .into(imageViewBackground);
    }

    private void setScore() {
        textViewScore.setText(String.valueOf(tvShow.getVoteAverage()));
        if (tvShow.getVoteAverage() >= 7) {
            textViewScore.setBackgroundColor(getResources().getColor(R.color.score_green));
        } else if (tvShow.getVoteAverage() < 7 && tvShow.getVoteAverage() > 5) {
            textViewScore.setBackgroundColor(getResources().getColor(R.color.score_yellow));
        } else {
            textViewScore.setBackgroundColor(getResources().getColor(R.color.score_red));
        }
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
            this.layoutGenre.addView(textView);
        }
    }

    private String getRuntime() {
        String result = "";
        for (int index = 0; index < tvShow.getEpisodeRunTime().size(); index++) {
            if (tvShow.getEpisodeRunTime().get(index) < 60) {
                int minute = tvShow.getEpisodeRunTime().get(index) % 60;
                result = minute +
                        getResources().getString(R.string.minute);
            } else {
                int hour = tvShow.getEpisodeRunTime().get(index) / 60;
                int minute = tvShow.getEpisodeRunTime().get(index) % 60;
                result = hour +
                        getResources().getString(R.string.hour) + " " +
                        minute +
                        getResources().getString(R.string.minute);
            }
            if (index + 1 < tvShow.getEpisodeRunTime().size()) {
                result = result + ", ";
            }
        }
        return result;
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
        outState.putInt(TVSHOW_ID, tvshow_id);
        outState.putParcelable(TVSHOW_DATA, tvShow);;
    }
}
