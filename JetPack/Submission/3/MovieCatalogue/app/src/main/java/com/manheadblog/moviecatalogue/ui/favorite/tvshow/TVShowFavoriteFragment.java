package com.manheadblog.moviecatalogue.ui.favorite.tvshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.adapter.OnTVShowItemClickCallback;
import com.manheadblog.moviecatalogue.data.resource.Resource;
import com.manheadblog.moviecatalogue.databinding.TvshowFavoriteFragmentBinding;
import com.manheadblog.moviecatalogue.entity.TVShow;
import com.manheadblog.moviecatalogue.ui.detail.tvshow.TVShowDetailActivity;
import com.manheadblog.moviecatalogue.utils.SimpleDialog;
import com.manheadblog.moviecatalogue.utils.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class TVShowFavoriteFragment extends Fragment implements OnTVShowItemClickCallback {

    private TvshowFavoriteViewModel mViewModel;
    private TVShowFavoriteAdapter adapter;
    private TvshowFavoriteFragmentBinding binding;

    public TVShowFavoriteFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tvshow_favorite_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            mViewModel = obtainViewModel(getActivity());
            adapter = new TVShowFavoriteAdapter(new ArrayList<TVShow>(), getContext());

            adapter.setOnItemClickCallback(this);
            binding.setLifecycleOwner(this);
            binding.recyclerViewTVShow.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
            binding.recyclerViewTVShow.setAdapter(adapter);

            mViewModel.tvshowsPaged().observe(this, successPage);
            binding.setViewModel(mViewModel);
        }

    }

    private Observer<Resource<List<TVShow>>> success = new Observer<Resource<List<TVShow>>>() {
        @Override
        public void onChanged(Resource<List<TVShow>> response) {
            if (response != null){
                switch (response.status){
                    case LOADING:
                        mViewModel.busy.set(true);
                        break;
                    case SUCCESS:
                        if (response.data != null){
                            adapter.setArrayList(response.data);
                        }
                        mViewModel.busy.set(false);
                        break;
                    case ERROR:
                        mViewModel.busy.set(false);
                        break;
                }
            }
        }
    };

    private Observer<Resource<PagedList<TVShow>>> successPage = new Observer<Resource<PagedList<TVShow>>>() {
        @Override
        public void onChanged(Resource<PagedList<TVShow>> response) {
            if (response != null){
                switch (response.status){
                    case LOADING:
                        mViewModel.busy.set(true);
                        break;
                    case SUCCESS:
                        if (!response.data.isEmpty()){
                            adapter.setArrayList(response.data);
                        }
                        mViewModel.busy.set(false);
                        break;
                    case ERROR:
                        mViewModel.busy.set(false);
                        break;
                }
            }
        }
    };


    @Override
    public void onItemClicked(TVShow item) {
        Intent intent = new Intent(getContext(), TVShowDetailActivity.class);
        intent.putExtra(TVShowDetailActivity.DATA, item);
        startActivity(intent);
    }

    @Override
    public void onItemFavorited(TVShow item) {
        if (item.isFavorite()){
            SimpleDialog simpleDialog = new SimpleDialog(getContext(), SimpleDialog.DialogType.YES_NO);
            simpleDialog.setMessage(getResources().getString(R.string.dialog_remove_favorite));
            simpleDialog.setCancelable(true);
            simpleDialog.setOnPosiviteButtonListener((dialog, which) -> {
                mViewModel.removeFavorite(item);
                showSnackbarMessage(getResources().getString(R.string.favorite_false));
            });
            simpleDialog.show();
        }
    }

    private void showSnackbarMessage(String message) {
        Snackbar.make(binding.recyclerViewTVShow, message, Snackbar.LENGTH_SHORT).show();
    }

    @NonNull
    private static TvshowFavoriteViewModel obtainViewModel(FragmentActivity activity){
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(TvshowFavoriteViewModel.class);
    }

}
