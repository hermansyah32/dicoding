package com.manheadblog.moviecatalogue.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.manheadblog.moviecatalogue.App;
import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.model.Movie;

import java.util.ArrayList;

public class MoviesLocalAdapter extends RecyclerView.Adapter<MoviesLocalAdapter.MovieViewHolder> {

    private ArrayList<Movie> movieArrayList;
    private OnItemClickCallaback onItemClickCallaback;

    public void setOnItemClickCallaback(OnItemClickCallaback onItemClickCallaback) {
        this.onItemClickCallaback = onItemClickCallaback;
    }

    public MoviesLocalAdapter(ArrayList<Movie> movieArrayList) {
        this.movieArrayList = movieArrayList;
    }

    public ArrayList<Movie> getMovieArrayList() {
        return movieArrayList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, final int position) {
        final Movie movie = movieArrayList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(movie.getURLPosterPath())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imageViewPoster);
        holder.textViewScore.setText(String.valueOf(movie.getVoteAverage()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallaback.OnItemClicked(movie);
            }
        });

        holder.imageViewLike.setImageDrawable(App.getContext().getResources().getDrawable(R.drawable.ic_favorite_black_24dp));

        holder.imageViewLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallaback.OnItemLiked(movie, position, holder.itemView);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewPoster;
        ImageView imageViewLike;
        TextView textViewScore;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewPoster = itemView.findViewById(R.id.imageViewMovie);
            imageViewLike = itemView.findViewById(R.id.imageViewMovieFavorite);
            textViewScore = itemView.findViewById(R.id.textViewScoreMovie);
        }
    }

    public void addItem(Movie data) {
        movieArrayList.add(data);
        notifyDataSetChanged();
    }

    public void addItems(ArrayList<Movie> items) {
        movieArrayList.addAll(items);
        notifyDataSetChanged();
    }

    public void removeItem(int position){
        movieArrayList.remove(position);
        notifyDataSetChanged();
    }

    public interface OnItemClickCallaback {
        void OnItemClicked(Movie data);
        void OnItemLiked(Movie data, int position, View itemView);
    }
}
