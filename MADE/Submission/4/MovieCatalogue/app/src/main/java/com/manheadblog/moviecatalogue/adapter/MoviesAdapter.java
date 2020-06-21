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

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private ArrayList<Movie> movieArrayList;
    private ArrayList<Movie> favoritemovieArrayList;
    private OnItemClickCallaback onItemClickCallaback;

    public void setOnItemClickCallaback(OnItemClickCallaback onItemClickCallaback) {
        this.onItemClickCallaback = onItemClickCallaback;
    }

    public MoviesAdapter(ArrayList<Movie> movieArrayList, ArrayList<Movie> favorite) {
        this.movieArrayList = movieArrayList;
        this.favoritemovieArrayList = favorite;
    }

    public ArrayList<Movie> getMovieArrayList() {
        return movieArrayList;
    }

    public ArrayList<Movie> getLocalMovieArrayList() {
        return favoritemovieArrayList;
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

        for (Movie movieDb: favoritemovieArrayList
             ) {
            if (movieDb.getId() == movie.getId()){
                movie.setIsLiked(true);
                movie.setDatabase_id(movieDb.getDatabase_id());
            }
        }

        if (movie.getIsLiked()){
            holder.imageViewLike.setImageDrawable(App.getContext().getResources().getDrawable(R.drawable.ic_favorite_black_24dp));
        }else {
            holder.imageViewLike.setImageDrawable(App.getContext().getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp));
        }

        holder.imageViewLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (movie.getIsLiked()){
                    onItemClickCallaback.OnItemLiked(true, movie, position, holder.itemView);
                }else {
                    onItemClickCallaback.OnItemLiked(false, movie, position, holder.itemView);
                }
            }
        });

        movieArrayList.get(position).setImageView(holder.imageViewLike);
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

    public void addItemLocal(Movie data, int position) {
        favoritemovieArrayList.add(data);
        movieArrayList.get(position).setIsLiked(true);
        movieArrayList.get(position).setDatabase_id(data.getDatabase_id());
        notifyDataSetChanged();
    }

    public void addItemsLocal(ArrayList<Movie> items) {
        favoritemovieArrayList.addAll(items);
        notifyDataSetChanged();
    }

    public void removeItemLocal(int position){
        Movie movie = movieArrayList.get(position);
        for (int i = 0; i < favoritemovieArrayList.size(); i++) {
            if (movie.getId() == favoritemovieArrayList.get(i).getId()){
                favoritemovieArrayList.remove(i);
                break;
            }
        }
        movieArrayList.get(position).setIsLiked(false);
        movieArrayList.get(position).setDatabase_id(0);
        notifyDataSetChanged();
    }

    public void removeItemLocalbyId(int id){
        Movie movie = null;
        int position = -1;
        for (int i = 0; i < movieArrayList.size(); i++) {
            if (movieArrayList.get(i).getId() == id){
                movie = movieArrayList.get(i);
                position = i;
                break;
            }
        }

        for (int i = 0; i < favoritemovieArrayList.size(); i++) {
            if (movie != null){
                if (movie.getId() == favoritemovieArrayList.get(i).getId()){
                    favoritemovieArrayList.remove(i);
                    break;
                }
            }

        }
        if (position >= 0){
            movieArrayList.get(position).setIsLiked(false);
            movieArrayList.get(position).setDatabase_id(0);

        }
        notifyDataSetChanged();
    }

    public interface OnItemClickCallaback {
        void OnItemClicked(Movie data);
        void OnItemLiked(boolean isLiked, Movie data, int position, View itemView);
    }
}
