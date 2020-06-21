package com.manheadblog.moviecatalogue.ui.favorite.tvshow;

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
import com.manheadblog.moviecatalogue.adapter.OnTVShowItemClickCallback;
import com.manheadblog.moviecatalogue.databinding.ItemTvshowDiscoverBinding;
import com.manheadblog.moviecatalogue.entity.TVShow;

import java.util.List;

public class TVShowFavoriteAdapter extends PagedListAdapter<TVShow, TVShowFavoriteAdapter.ViewHolder> {

    private List<TVShow> arrayList;
    private OnTVShowItemClickCallback onItemClickCallback;
    private Context context;

    public TVShowFavoriteAdapter(List<TVShow> arrayList, Context context) {
        super(DIFF_CALLBACK);
        this.arrayList = arrayList;
        this.context = context;
    }

    public void setArrayList(List<TVShow> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public void setOnItemClickCallback(OnTVShowItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTvshowDiscoverBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_tvshow_discover, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TVShow tvShow = arrayList.get(position);
        holder.bind(tvShow);
        holder.binding.setItemClicked(onItemClickCallback);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemTvshowDiscoverBinding binding;

        public ViewHolder(@NonNull ItemTvshowDiscoverBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind (TVShow tvShow){
            binding.setViewModel(tvShow);
            binding.executePendingBindings();
        }
    }

    private static DiffUtil.ItemCallback<TVShow> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<TVShow>() {
                @Override
                public boolean areItemsTheSame(@NonNull TVShow oldItem, @NonNull TVShow newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull TVShow oldItem, @NonNull TVShow newItem) {
                    return oldItem.equals(newItem);
                }
            };

    TVShow getItemById(int swipedPosition) {
        return getItem(swipedPosition);
    }

    //Special Action
    public void removeItem(TVShow item){
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
