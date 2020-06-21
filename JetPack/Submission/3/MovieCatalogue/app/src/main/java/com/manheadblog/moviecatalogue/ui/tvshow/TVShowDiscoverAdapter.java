package com.manheadblog.moviecatalogue.ui.tvshow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.adapter.OnTVShowItemClickCallback;
import com.manheadblog.moviecatalogue.databinding.ItemTvshowDiscoverBinding;
import com.manheadblog.moviecatalogue.entity.TVShow;

import java.util.List;

public class TVShowDiscoverAdapter extends RecyclerView.Adapter<TVShowDiscoverAdapter.ViewHolder> {

    private List<TVShow> arrayList;
    private OnTVShowItemClickCallback onItemClickCallback;
    private Context context;

    public void setArrayList(List<TVShow> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public void setOnItemClickCallback(OnTVShowItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public TVShowDiscoverAdapter(List<TVShow> arrayList, Context context) {
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
