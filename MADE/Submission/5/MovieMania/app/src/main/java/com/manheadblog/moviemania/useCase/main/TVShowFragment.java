package com.manheadblog.moviemania.useCase.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.manheadblog.moviemania.R;
import com.manheadblog.moviemania.adapter.TVShowAdapter;
import com.manheadblog.moviemania.databinding.FragmentTvshowBinding;
import com.manheadblog.moviemania.db.TVShowHelper;
import com.manheadblog.moviemania.model.TVShow;
import com.manheadblog.moviemania.useCase.tvdetail.TVShowDetailActivity;

import java.util.ArrayList;

public class TVShowFragment extends Fragment implements TVShowAdapter.OnItemClickCallaback {

    private static final String LIST_DATA = "LIST_DATA";

    private TVShowFragmentViewModel mViewModel;
    private TVShowAdapter tvAdapter;
    private TVShowHelper tvShowHelper;
    private FragmentTvshowBinding binding;

    public static TVShowFragment newInstance() {
        return new TVShowFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tvshow, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvShowHelper = TVShowHelper.getInstance();

        mViewModel = ViewModelProviders.of(this).get(TVShowFragmentViewModel.class);
        mViewModel.getTVShows().observe(this, getTVShows);
        binding.setViewModel(mViewModel);
        binding.setLifecycleOwner(this);

        binding.recyclerVewTVShows.setHasFixedSize(true);
        if (savedInstanceState == null){
            tvAdapter = new TVShowAdapter(new ArrayList<TVShow>());
            mViewModel.setData();
        }else {
            tvAdapter = new TVShowAdapter(savedInstanceState.<TVShow>getParcelableArrayList(LIST_DATA));
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

    private void showSnackbarMessage(String message) {
        Snackbar.make(binding.recyclerVewTVShows, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(LIST_DATA, tvAdapter.getTvShowArrayList());
    }

}
