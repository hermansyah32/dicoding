package com.manheadblog.moviecatalogue.ui.favorite.movie;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.adapter.OnMovieItemClickCallback;
import com.manheadblog.moviecatalogue.databinding.ItemMovieDiscoverBinding;
import com.manheadblog.moviecatalogue.entity.Movie;

import java.util.List;

public class MovieFavoriteAdapter extends PagedListAdapter<Movie, MovieFavoriteAdapter.ViewHolder> {

    private List<Movie> arrayList;
    private OnMovieItemClickCallback onItemClickCallback;
    private Context context;

    public MovieFavoriteAdapter(List<Movie> arrayList, Context context) {
        super(DIFF_CALLBACK);
        this.arrayList = arrayList;
        this.context = context;
    }

    public void setArrayList(List<Movie> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public void setOnItemClickCallback(OnMovieItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
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

    private static DiffUtil.ItemCallback<Movie> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Movie>() {
                @Override
                public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
                    return oldItem.equals(newItem);
                }
            };

    Movie getItemById(int swipedPosition) {
        return getItem(swipedPosition);
    }

    //Special Action
    public void removeItem(Movie item){
        int position = -1;
        for (int i = 0; i < arrayList.size(); i++) {
            if (item.getId() == arrayList.get(i).getId()){
                position = i;
            }
        }

        if (position > 0){
            arrayList.remove(position);
        }
        notifyDataSetChanged();
    }

}
