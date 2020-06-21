package com.manheadblog.moviecatalogue.useCase.movie_detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.model.Movie;

import java.text.NumberFormat;
import java.util.Locale;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailView {
    public static final String MOVIE = "data_movie";

    TextView textViewTitle, textViewDescription, textViewStatus, textViewOriginalLaguange, textViewBudget,
            textViewRevenue, textViewScore, textViewRuntime;
    ImageView imageViewBackground, imageViewPoster;
    TableLayout tableLayoutReleaseInformation, tableLayoutCrew;
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        textViewTitle = findViewById(R.id.textviewDetailTitle);
        textViewDescription = findViewById(R.id.textviewDetailDescription);
        textViewStatus = findViewById(R.id.textviewDetailStatus);
        textViewOriginalLaguange = findViewById(R.id.textviewDetailoriginalLanguage);
        textViewBudget = findViewById(R.id.textviewDetailBudget);
        textViewRevenue = findViewById(R.id.textviewDetailRevenue);
        textViewScore = findViewById(R.id.textviewDetailScore);
        textViewRuntime = findViewById(R.id.textviewDetailRuntime);
        imageViewBackground = findViewById(R.id.imageviewDetailPosterBackground);
        imageViewPoster = findViewById(R.id.imageviewDetailPoster);
        tableLayoutReleaseInformation = findViewById(R.id.detail_release_information_layout);
        tableLayoutCrew = findViewById(R.id.detail_crew_layout);

        movie = getIntent().getParcelableExtra(MOVIE);

        final MovieDetailPresenter movieDetailPresenter = new MovieDetailPresenter(this, movie);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void showMovie(Movie movie) {
        textViewTitle.setText(movie.getTitle());
        textViewDescription.setText(movie.getDescription());
        textViewStatus.setText(movie.getStatus());
        textViewOriginalLaguange.setText(movie.getOriginal_language());
        textViewScore.setText(String.valueOf(movie.getScore()));
        textViewRuntime.setText(movie.getRuntime());

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.CANADA);
        String budget = numberFormat.format(movie.getBudget());
        String revenue = numberFormat.format(movie.getRevenue());
        if (movie.getBudget() < 1) budget = "-";
        if (movie.getRevenue() < 1) revenue = "-";

        textViewBudget.setText(budget);
        textViewRevenue.setText(revenue);

        Glide.with(this).load(getDrawable(movie.getPoster())).into(imageViewPoster);
        Glide.with(this).load(getDrawable(movie.getPoster()))
                .apply(bitmapTransform(new BlurTransformation(5)))
                .into(imageViewBackground);
    }

    @Override
    public void showReleaseInformation(String[][] data) {
        tableLayoutReleaseInformation.removeAllViews();

        for (int i = 0; i < data.length; i++) {
            TableRow tableRow = new TableRow(MovieDetailActivity.this);
            tableRow.setLayoutParams(
                    new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
            TextView textViewReleaseMode = new TextView(MovieDetailActivity.this);
            textViewReleaseMode.setText(data[i][0]);
            textViewReleaseMode.setLayoutParams(
                    new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
            TextView textViewSeparator = new TextView(MovieDetailActivity.this);
            textViewSeparator.setText(" : ");
            textViewSeparator.setLayoutParams(
                    new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
            TextView textViewReleaseDate = new TextView(MovieDetailActivity.this);
            textViewReleaseDate.setText(data[i][1]);
            textViewReleaseDate.setLayoutParams(
                    new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
            TextView textViewReleasePlace = new TextView(MovieDetailActivity.this);
            textViewReleasePlace.setText(" " + data[i][2]);
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

    @Override
    public void showFeaturedCrew(String[][] crew) {
        tableLayoutCrew.removeAllViews();

        for (int i = 0; i < crew.length; i++) {
            TableRow tableRow = new TableRow(MovieDetailActivity.this);
            tableRow.setLayoutParams(
                    new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
            TextView textViewCrewName = new TextView(MovieDetailActivity.this);
            textViewCrewName.setText(crew[i][0]);
            textViewCrewName.setLayoutParams(
                    new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
            TextView textViewSeparator = new TextView(MovieDetailActivity.this);
            textViewSeparator.setText(" as ");
            textViewSeparator.setLayoutParams(
                    new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
            TextView textViewCrewPosition = new TextView(MovieDetailActivity.this);
            textViewCrewPosition.setText(crew[i][1]);
            textViewCrewPosition.setLayoutParams(
                    new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));

            tableRow.addView(textViewCrewName);
            tableRow.addView(textViewSeparator);
            tableRow.addView(textViewCrewPosition);

            tableLayoutCrew.addView(tableRow);
        }
    }
}
