package com.manheadblog.moviecatalogue.useCase.tvdetail;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.model.TVShow;

import java.util.Objects;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class TVShowDetailActivity extends AppCompatActivity {

    public static final String TVSHOW_DATA = "TVSHOW_DATA";
    private TVShow tvShow;
    private TextView textViewTItle, textViewDescription, textViewStatus, textViewLanguage,
            textViewRuntime, textViewScore, textViewGenre;
    private ImageView imageViewBackground, imageViewPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshow_detail);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvShow = getIntent().getParcelableExtra(TVSHOW_DATA);

        textViewTItle = findViewById(R.id.textViewTVShowTitle);
        textViewDescription = findViewById(R.id.textViewTVShowDescription);
        textViewStatus = findViewById(R.id.textViewTVShowStatus);
        textViewLanguage = findViewById(R.id.textViewTVShowLanguage);
        textViewRuntime = findViewById(R.id.textViewTVShowRuntime);
        textViewScore = findViewById(R.id.textViewTVShowScore);
        textViewGenre = findViewById(R.id.textViewTVShowGenre);
        imageViewBackground = findViewById(R.id.imageViewTVShowBackground);
        imageViewPoster = findViewById(R.id.imageViewTVShowPoster);

        showTVShow();
    }

    private void showTVShow() {
        textViewTItle.setText(tvShow.getTitle());
        textViewDescription.setText(tvShow.getDescription());
        textViewStatus.setText(tvShow.getStatus());
        textViewLanguage.setText(tvShow.getOriginal_language());
        textViewRuntime.setText(tvShow.getRuntime());
        textViewGenre.setText(tvShow.getGenre());
        setScore();

        Glide.with(this).load(getDrawable(tvShow.getPoster())).into(imageViewPoster);
        Glide.with(this).load(getDrawable(tvShow.getPoster()))
                .apply(bitmapTransform(new BlurTransformation(5)))
                .into(imageViewBackground);
    }

    private void setScore(){
        textViewScore.setText(String.valueOf(tvShow.getScore()));
        if (tvShow.getScore()>=70){
            textViewScore.setBackgroundColor(getResources().getColor(R.color.score_green));
        }else if (tvShow.getScore()<70 && tvShow.getScore()>50){
            textViewScore.setBackgroundColor(getResources().getColor(R.color.score_yellow));
        }else {
            textViewScore.setBackgroundColor(getResources().getColor(R.color.score_red));
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
}
