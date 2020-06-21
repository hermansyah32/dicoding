package com.manheadblog.moviecatalogue.useCase.favorite;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.adapter.TVShowLocalAdapter;
import com.manheadblog.moviecatalogue.databinding.FragmentTvshowFragmentBinding;
import com.manheadblog.moviecatalogue.db.TVShowHelper;
import com.manheadblog.moviecatalogue.model.TVShow;
import com.manheadblog.moviecatalogue.useCase.tvdetail.TVShowDetailActivity;

import java.util.ArrayList;

public class FragmentTVShow extends Fragment implements TVShowLocalAdapter.OnItemClickCallaback{

    private static final String LIST_DATA = "LIST_DATA";

    private FragmentTvshowViewModel mViewModel;
    private TVShowLocalAdapter tvAdapter;
    private TVShowHelper tvShowHelper;
    FragmentTvshowFragmentBinding binding;

    public static FragmentTVShow newInstance() {
        return new FragmentTVShow();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tvshow_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvShowHelper = TVShowHelper.getInstance();

        mViewModel = ViewModelProviders.of(this).get(FragmentTvshowViewModel.class);
        mViewModel.getTVShows().observe(this, getTVShows);
        binding.setViewModel(mViewModel);
        binding.setLifecycleOwner(this);

        binding.recyclerVewTVShows.setHasFixedSize(true);
        if (savedInstanceState == null){
            tvAdapter = new TVShowLocalAdapter(new ArrayList<TVShow>());
            mViewModel.setData();
        }else {
            tvAdapter = new TVShowLocalAdapter(savedInstanceState.<TVShow>getParcelableArrayList(LIST_DATA));
        }

        binding.recyclerVewTVShows.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        binding.recyclerVewTVShows.setNestedScrollingEnabled(false);
        binding.recyclerVewTVShows.setAdapter(tvAdapter);
        tvAdapter.setOnItemClickCallaback(this);
    }

    private Observer<ArrayList<TVShow>> getTVShows = new Observer<ArrayList<TVShow>>() {
        @Override
        public void onChanged(ArrayList<TVShow> items) {
            if (items != null) {
                tvAdapter.addItems(items);
                binding.recyclerVewTVShows.setAdapter(tvAdapter);
            }
        }
    };

    @Override
    public void OnItemClicked(TVShow data) {
        Intent intent = new Intent(getActivity(), TVShowDetailActivity.class);
        intent.putExtra(TVShowDetailActivity.TVSHOW_ID, data.getId());
        startActivity(intent);
    }

    @Override
    public void OnItemLiked(TVShow data, int position, View itemView) {
        ImageView imageView = itemView.findViewById(R.id.imageViewTVShowFavorite);
        long result = tvShowHelper.delete(data.getDatabase_id());
        if (result>0){
            showSnackbarMessage(getResources().getString(R.string.tvshow_disliked));
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp));
            ((FavoriteActivity) getActivity()).addRemovedTVShow(data.getId());
            tvAdapter.removeItem(position);
        }else {
            showSnackbarMessage(getResources().getString(R.string.tvshow_failed_disliked));
        }
    }

    private void showSnackbarMessage(String message) {
        Snackbar.make(binding.recyclerVewTVShows, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(LIST_DATA, tvAdapter.getTvShowArrayList());
    }
}
