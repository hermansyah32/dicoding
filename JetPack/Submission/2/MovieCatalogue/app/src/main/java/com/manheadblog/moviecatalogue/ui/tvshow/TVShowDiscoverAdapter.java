package com.manheadblog.moviecatalogue.ui.tvshow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.databinding.ItemTvshowDiscoverBinding;
import com.manheadblog.moviecatalogue.entity.TVShow;

import java.util.ArrayList;

public class TVShowDiscoverAdapter extends RecyclerView.Adapter<TVShowDiscoverAdapter.ViewHolder> {

    private ArrayList<TVShow> arrayList;
    private OnItemClickCallback onItemClickCallback;
    private Context context;

    public void setArrayList(ArrayList<TVShow> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public TVShowDiscoverAdapter(ArrayList<TVShow> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
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

    public interface OnItemClickCallback{
        void onItemClicked(TVShow item);
    }
}
