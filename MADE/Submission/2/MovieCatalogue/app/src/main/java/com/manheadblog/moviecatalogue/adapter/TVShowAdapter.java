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
import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.model.TVShow;

import java.util.ArrayList;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder> {

    private ArrayList<TVShow> tvShowArrayList;
    private OnItemClickCallaback onItemClickCallaback;

    public void setOnItemClickCallaback(OnItemClickCallaback onItemClickCallaback) {
        this.onItemClickCallaback = onItemClickCallaback;
    }

    public TVShowAdapter(ArrayList<TVShow> tvShowArrayList) {
        this.tvShowArrayList = tvShowArrayList;
    }

    @NonNull
    @Override
    public TVShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tvshow, parent, false);
        return new TVShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowViewHolder holder, int position) {
        final TVShow tvShow = tvShowArrayList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(tvShow.getPoster())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imageViewPoster);
        holder.textViewScore.setText(String.valueOf(tvShow.getScore()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallaback.OnItemClicked(tvShow);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tvShowArrayList.size();
    }

    class TVShowViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewPoster;
        TextView textViewScore;

        TVShowViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.imageViewTVShow);
            textViewScore = itemView.findViewById(R.id.textViewScoreTVShow);
        }
    }

    public interface OnItemClickCallaback {
        void OnItemClicked(TVShow data);
    }
}
