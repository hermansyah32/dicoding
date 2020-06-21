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
import com.manheadblog.moviecatalogue.model.TVShow;

import java.util.ArrayList;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder> {

    private ArrayList<TVShow> tvShowArrayList;
    private ArrayList<TVShow> favtvShowArrayList;
    private OnItemClickCallaback onItemClickCallaback;

    public void setOnItemClickCallaback(OnItemClickCallaback onItemClickCallaback) {
        this.onItemClickCallaback = onItemClickCallaback;
    }

    public TVShowAdapter(ArrayList<TVShow> tvShowArrayList, ArrayList<TVShow> favtvShowArrayList) {
        this.tvShowArrayList = tvShowArrayList;
        this.favtvShowArrayList = favtvShowArrayList;
    }

    @NonNull
    @Override
    public TVShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tvshow, parent, false);
        return new TVShowViewHolder(view);
    }

    public ArrayList<TVShow> getTvShowArrayList() {
        return tvShowArrayList;
    }
    public ArrayList<TVShow> getLocalTvShowArrayList() {
        return favtvShowArrayList;
    }

    @Override
    public void onBindViewHolder(@NonNull final TVShowViewHolder holder, final int position) {
        final TVShow tvShow = tvShowArrayList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(tvShow.getURLPosterPath())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imageViewPoster);
        holder.textViewScore.setText(String.valueOf(tvShow.getVoteAverage()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallaback.OnItemClicked(tvShow);
            }
        });

        for (TVShow tvShowDB: favtvShowArrayList
        ) {
            if (tvShowDB.getId() == tvShow.getId()){
                tvShow.setIsLiked(true);
                tvShow.setDatabase_id(tvShowDB.getDatabase_id());
            }
        }

        if (tvShow.getIsLiked()){
            holder.imageViewLike.setImageDrawable(App.getContext().getResources().getDrawable(R.drawable.ic_favorite_black_24dp));
        }else {
            holder.imageViewLike.setImageDrawable(App.getContext().getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp));
        }

        holder.imageViewLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvShow.getIsLiked()){
                    onItemClickCallaback.OnItemLiked(true, tvShow, position, holder.itemView);
                }else {
                    onItemClickCallaback.OnItemLiked(false, tvShow, position, holder.itemView);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return tvShowArrayList.size();
    }

    class TVShowViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewPoster;
        ImageView imageViewLike;
        TextView textViewScore;

        TVShowViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.imageViewTVShow);
            imageViewLike = itemView.findViewById(R.id.imageViewTVShowFavorite);
            textViewScore = itemView.findViewById(R.id.textViewScoreTVShow);
        }
    }

    public void addItem(TVShow data) {
        tvShowArrayList.add(data);
        notifyDataSetChanged();
    }

    public void addItems(ArrayList<TVShow> items) {
        tvShowArrayList.addAll(items);
        notifyDataSetChanged();
    }

    public void addItemLocal(TVShow data, int position) {
        favtvShowArrayList.add(data);
        tvShowArrayList.get(position).setIsLiked(true);
        tvShowArrayList.get(position).setDatabase_id(data.getDatabase_id());
        notifyDataSetChanged();
    }

    public void addItemsLocal(ArrayList<TVShow> items) {
        favtvShowArrayList.addAll(items);
        notifyDataSetChanged();
    }

    public void removeItemLocal(int position){
        TVShow tvShow = tvShowArrayList.get(position);
        for (int i = 0; i < favtvShowArrayList.size(); i++) {
            if (tvShow.getId() == favtvShowArrayList.get(i).getId()){
                favtvShowArrayList.remove(i);
                break;
            }
        }
        tvShowArrayList.get(position).setIsLiked(false);
        tvShowArrayList.get(position).setDatabase_id(0);
        notifyDataSetChanged();
    }

    public void removeItemLocalbyId(int id){
        TVShow tvShow = null;
        int position = -1;
        for (int i = 0; i < tvShowArrayList.size(); i++) {
            if (tvShowArrayList.get(i).getId() == id){
                tvShow = tvShowArrayList.get(i);
                position = i;
                break;
            }
        }

        for (int i = 0; i < favtvShowArrayList.size(); i++) {
            if (tvShow != null){
                if (tvShow.getId() == favtvShowArrayList.get(i).getId()){
                    favtvShowArrayList.remove(i);
                    break;
                }
            }

        }
        if (position >= 0){
            tvShowArrayList.get(position).setIsLiked(false);
            tvShowArrayList.get(position).setDatabase_id(0);

        }
        notifyDataSetChanged();
    }

    public interface OnItemClickCallaback {
        void OnItemClicked(TVShow data);
        void OnItemLiked(boolean isLiked, TVShow data, int position, View itemView);
    }
}
