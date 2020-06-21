package com.manheadblog.moviecatalogue.useCase.moviedetail;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.model.Movie;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String MOVIE_DATA = "MOVIE_DATA";
    private Movie movie;
    private TextView textViewTItle, textViewDescription, textViewStatus, textViewLanguage,
            textViewBudget, textViewRevenue, textViewRuntime, textViewScore, textViewGenre, textViewRelease;
    private TableLayout tableLayoutReleaseInformation;
    private ImageView imageViewBackground, imageViewPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        movie = getIntent().getParcelableExtra(MOVIE_DATA);

        textViewTItle = findViewById(R.id.textViewMovieTitle);
        textViewDescription = findViewById(R.id.textViewMovieDescription);
        textViewStatus = findViewById(R.id.textViewMovieStatus);
        textViewLanguage = findViewById(R.id.textViewMovieLanguage);
        textViewBudget = findViewById(R.id.textViewMovieBudget);
        textViewRevenue = findViewById(R.id.textViewMovieRevenue);
        textViewRuntime = findViewById(R.id.textViewMovieRuntime);
        textViewScore = findViewById(R.id.textViewMovieScore);
        textViewGenre = findViewById(R.id.textViewMovieGenre);
        textViewRelease = findViewById(R.id.textViewMovieRelease);
        imageViewBackground = findViewById(R.id.imageViewMovieBackground);
        imageViewPoster = findViewById(R.id.imageViewMoviePoster);
        tableLayoutReleaseInformation = findViewById(R.id.tablelayoutMovieRelease);

        showMovie();
    }

    private void showMovie() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.CANADA);
        String budget = numberFormat.format(movie.getBudget());
        String revenue = numberFormat.format(movie.getRevenue());
        if (movie.getBudget() < 1) budget = "-";
        if (movie.getRevenue() < 1) revenue = "-";

        Glide.with(this).load(getDrawable(movie.getPoster())).into(imageViewPoster);
        Glide.with(this).load(getDrawable(movie.getPoster()))
                .apply(bitmapTransform(new BlurTransformation(5)))
                .into(imageViewBackground);

        textViewTItle.setText(movie.getTitle());
        textViewDescription.setText(movie.getDescription());
        textViewStatus.setText(movie.getStatus());
        textViewLanguage.setText(movie.getOriginal_language());
        textViewRuntime.setText(movie.getRuntime());
        textViewBudget.setText(budget);
        textViewRevenue.setText(revenue);
        textViewGenre.setText(movie.getGenre());
        setScore();
        generateReleaseInformation(movie.getRelease_information());
    }

    private void generateReleaseInformation(String releaseData) {
        tableLayoutReleaseInformation.removeAllViews();

        String[][] data;
        String[] resultData = releaseData.split(";");
        data = new String[resultData.length][3];
        for (int i = 0; i < resultData.length; i++) {
            String[] item = resultData[i].split(":");
            data[i][0] = item[0];
            data[i][1] = item[1];
            data[i][2] = item[2];
        }

        textViewRelease.setText(data[0][1]);

        for (String[] datum : data) {
            TableRow tableRow = new TableRow(MovieDetailActivity.this);
            tableRow.setLayoutParams(
                    new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
            TextView textViewReleaseMode = new TextView(MovieDetailActivity.this);
            textViewReleaseMode.setText(datum[0]);
            textViewReleaseMode.setTextColor(getResources().getColor(R.color.dark_text_subtitle));
            textViewReleaseMode.setLayoutParams(
                    new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
            TextView textViewSeparator = new TextView(MovieDetailActivity.this);
            textViewSeparator.setText(" : ");
            textViewSeparator.setTextColor(getResources().getColor(R.color.dark_text_subtitle));
            textViewSeparator.setLayoutParams(
                    new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
            TextView textViewReleaseDate = new TextView(MovieDetailActivity.this);
            textViewReleaseDate.setText(datum[1]);
            textViewReleaseDate.setTextColor(getResources().getColor(R.color.dark_text_subtitle));
            textViewReleaseDate.setLayoutParams(
                    new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
            TextView textViewReleasePlace = new TextView(MovieDetailActivity.this);
            textViewReleasePlace.setText(datum[2]);
            textViewReleasePlace.setTextColor(getResources().getColor(R.color.dark_text_subtitle));
            textViewReleasePlace.setLayoutParams(
                    new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));

            tableRow.addView(textViewReleaseMode);
            tableRow.addView(textViewSeparator);
            tableRow.addView(textViewReleaseDate);
            tableRow.addView(textViewReleasePlace);

            tableLayoutReleaseInformation.addView(tableRow);
        }
    }

    private void setScore(){
        textViewScore.setText(String.valueOf(movie.getScore()));
        if (movie.getScore()>=70){
            textViewScore.setBackgroundColor(getResources().getColor(R.color.score_green));
        }else if (movie.getScore()<70 && movie.getScore()>50){
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
