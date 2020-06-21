package com.manheadblog.moviecatalogue.useCase.main;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.adapter.TVShowAdapter;
import com.manheadblog.moviecatalogue.model.TVShow;
import com.manheadblog.moviecatalogue.useCase.tvdetail.TVShowDetailActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowsFragment extends Fragment implements TVShowAdapter.OnItemClickCallaback, View.OnClickListener {
    private static final String PAGE = "PAGE";
    private static final String LIST_DATA = "LIST_DATA";

    private TVShowsFragmentViewModel viewModel;
    private TVShowAdapter tvShowAdapter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private Button buttonLoad;
    private int page;

    public TVShowsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_tvshows, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        page = 1;

        recyclerView = view.findViewById(R.id.recyclerVewTVShows);
        progressBar = view.findViewById(R.id.loadingBar);
        recyclerView.setHasFixedSize(true);
        buttonLoad = view.findViewById(R.id.buttonLoad);
        buttonLoad.setOnClickListener(this);

        viewModel = ViewModelProviders.of(this).get(TVShowsFragmentViewModel.class);
        viewModel.getTVShows().observe(this, getTVShows);

        if (savedInstanceState == null){
            showLoading(true);
            viewModel.setData(page);
            tvShowAdapter = new TVShowAdapter(new ArrayList<TVShow>());
        }else {
            showLoading(true);
            page = savedInstanceState.getInt(PAGE);
            tvShowAdapter = new TVShowAdapter(savedInstanceState.<TVShow>getParcelableArrayList(LIST_DATA));
        }

        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        recyclerView.setAdapter(tvShowAdapter);
        tvShowAdapter.setOnItemClickCallaback(this);
    }

    private Observer<ArrayList<TVShow>> getTVShows = new Observer<ArrayList<TVShow>>() {
        @Override
        public void onChanged(ArrayList<TVShow> items) {
            if (items != null) {
                tvShowAdapter.addItems(items);
                showLoading(false);
            }
        }
    };

    @Override
    public void OnItemClicked(TVShow data) {
        Intent intent = new Intent(getActivity(), TVShowDetailActivity.class);
        intent.putExtra(TVShowDetailActivity.TVSHOW_ID, data.getId());
        startActivity(intent);
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
            buttonLoad.setVisibility(View.GONE);
            recyclerView.setEnabled(false);
        } else {
            progressBar.setVisibility(View.GONE);
            buttonLoad.setVisibility(View.VISIBLE);
            recyclerView.setEnabled(false);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PAGE, page);
        outState.putParcelableArrayList(LIST_DATA, tvShowAdapter.getTvShowArrayList());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonLoad){
            page +=1;
            viewModel.setData(page);
            showLoading(true);
        }
    }
}
