package com.manheadblog.moviecatalogue.ui.movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.databinding.ItemMovieDiscoverBinding;
import com.manheadblog.moviecatalogue.entity.Movie;

import java.util.ArrayList;

public class MovieDiscoverAdapter extends RecyclerView.Adapter<MovieDiscoverAdapter.ViewHolder> {

    private ArrayList<Movie> arrayList;
    private OnItemClickCallback onItemClickCallback;
    private Context context;

    public void setArrayList(ArrayList<Movie> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public MovieDiscoverAdapter(ArrayList<Movie> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieDiscoverBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_movie_discover, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = arrayList.get(position);
        holder.bind(movie);
        holder.binding.setItemClicked(onItemClickCallback);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemMovieDiscoverBinding binding;

        public ViewHolder(@NonNull ItemMovieDiscoverBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind (Movie movie){
            binding.setViewModel(movie);
            binding.executePendingBindings();
        }
    }

    public interface OnItemClickCallback{
        void onItemClicked(Movie item);
    }
}
