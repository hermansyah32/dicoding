package com.manheadblog.moviecatalogue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.model.Movie;

import java.util.ArrayList;

public class ListviewMainAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Movie> movies;

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public ListviewMainAdapter(Context context) {
        this.context = context;
        movies = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listviewmain, parent, false);
        }
        ViewHolder viewHolder = new ViewHolder(convertView);
        Movie movie = (Movie) getItem(position);
        viewHolder.bind(movie);
        return convertView;
    }

    private class ViewHolder {
        private Context context;
        private ImageView imageViewPoster;
        private TextView textViewTitle, textViewReleaseDate, textViewScore, textViewDescription;
        private LinearLayout linearLayoutGenre;

        public ViewHolder(View view) {
            context = view.getContext();
            imageViewPoster = view.findViewById(R.id.imageviewItemPoster);
            textViewTitle = view.findViewById(R.id.textviewItemTitle);
            textViewDescription = view.findViewById(R.id.textviewItemDescription);
            textViewReleaseDate = view.findViewById(R.id.textviewItemRelease);
            textViewScore = view.findViewById(R.id.textviewItemScore);
            linearLayoutGenre = view.findViewById(R.id.movie_genre_item_layout);
        }

        void bind(Movie movie) {
            textViewTitle.setText(movie.getTitle());
            textViewReleaseDate.setText(movie.getReleaseDate());
            textViewDescription.setText(movie.getDescription());
            imageViewPoster.setImageResource(movie.getPoster());

            // configure textview score
            textViewScore.setText(String.valueOf(movie.getScore()));
            if (movie.getScore() >= 70) {
                textViewScore.setBackgroundResource(R.drawable.round_shape_score_green);
            } else if (movie.getScore() < 70 && movie.getScore() > 50) {
                textViewScore.setBackgroundResource(R.drawable.round_shape_score_orange);
            } else {
                textViewScore.setBackgroundResource(R.drawable.round_shape_score_red);
            }

            // configure textview genre
            String[] genre = movie.getGenre().split(";");
            linearLayoutGenre.removeAllViews();
            // genre with more than 3 will show etc at the 4th item
            if (genre.length < 4) {
                for (int i = 0; i < genre.length; i++) {
                    TextView textView = new TextView(context);
                    textView.setText(genre[i]);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.rightMargin = 8;
                    textView.setLayoutParams(layoutParams);
                    textView.setBackgroundResource(R.drawable.genre_textview);
                    linearLayoutGenre.addView(textView);
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    TextView textView = new TextView(context);
                    textView.setText(genre[i]);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.rightMargin = 8;
                    textView.setLayoutParams(layoutParams);
                    textView.setBackgroundResource(R.drawable.genre_textview);
                    linearLayoutGenre.addView(textView);
                }
                TextView textView = new TextView(context);
                textView.setText("Etc");
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.rightMargin = 8;
                textView.setLayoutParams(layoutParams);
                textView.setPadding(4, 4, 4, 4);
                textView.setBackgroundResource(R.drawable.genre_textview);
                linearLayoutGenre.addView(textView);
            }
        }
    }
}
