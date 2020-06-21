package com.manheadblog.moviecatalogue.useCase.main;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.adapter.TVShowAdapter;
import com.manheadblog.moviecatalogue.db.TVShowHelper;
import com.manheadblog.moviecatalogue.model.TVShow;
import com.manheadblog.moviecatalogue.useCase.favorite.FavoriteActivity;
import com.manheadblog.moviecatalogue.useCase.tvdetail.TVShowDetailActivity;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowsFragment extends Fragment implements TVShowAdapter.OnItemClickCallaback, View.OnClickListener {
    private static final String PAGE = "PAGE";
    private static final String LIST_DATA = "LIST_DATA";
    private static final String LIST_DATA_LOCAL = "LIST_DATA_LOCAL";

    private TVShowsFragmentViewModel viewModel;
    private TVShowAdapter tvShowAdapter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private Button buttonLoad;
    private int page;
    private TVShowHelper tvShowHelper;

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

        tvShowHelper = TVShowHelper.getInstance();

        viewModel = ViewModelProviders.of(this).get(TVShowsFragmentViewModel.class);
        viewModel.getTVShows().observe(this, getTVShows);
        viewModel.getLocalTVShows().observe(this, getTVShowsLocal);

        if (savedInstanceState == null){
            showLoading(true);
            viewModel.setData(page);
            tvShowAdapter = new TVShowAdapter(new ArrayList<TVShow>(), new ArrayList<TVShow>());
        }else {
            showLoading(true);
            page = savedInstanceState.getInt(PAGE);
            tvShowAdapter = new TVShowAdapter(savedInstanceState.<TVShow>getParcelableArrayList(LIST_DATA),
                    savedInstanceState.<TVShow>getParcelableArrayList(LIST_DATA_LOCAL));
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

    private Observer<ArrayList<TVShow>> getTVShowsLocal = new Observer<ArrayList<TVShow>>() {
        @Override
        public void onChanged(ArrayList<TVShow> items) {
            if (items != null) {
                tvShowAdapter.addItemsLocal(items);
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
    public void OnItemLiked(boolean isLiked, TVShow data, int position, View itemView) {
        ImageView imageView = itemView.findViewById(R.id.imageViewTVShowFavorite);
        if (!isLiked){
            long result = tvShowHelper.insert(data);
            if (result>0){
                showSnackbarMessage(getResources().getString(R.string.tvshow_liked));
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_black_24dp));
                data.setDatabase_id(result);
                tvShowAdapter.addItemLocal(data, position);
            }else {
                showSnackbarMessage(getResources().getString(R.string.tvshow_failed_liked));
            }
        }else {
            long result = tvShowHelper.delete(data.getDatabase_id());
            if (result>0){
                showSnackbarMessage(getResources().getString(R.string.tvshow_disliked));
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp));
                tvShowAdapter.removeItemLocal(position);
            }else {
                showSnackbarMessage(getResources().getString(R.string.tvshow_failed_disliked));
            }
        }
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
        outState.putParcelableArrayList(LIST_DATA_LOCAL, tvShowAdapter.getLocalTvShowArrayList());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonLoad){
            page +=1;
            viewModel.setData(page);
            showLoading(true);
        }
    }

    private void showSnackbarMessage(String message) {
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null){
            if (requestCode == MainActivity.MainActivityResult){
                if (resultCode == RESULT_OK){
                    //Refresh local database disini
                    ArrayList<Integer> removedTVShow = data.getIntegerArrayListExtra(FavoriteActivity.REMOVED_TVSHOW);
                    for (int i = 0; i < removedTVShow.size(); i++) {
                        tvShowAdapter.removeItemLocalbyId(removedTVShow.get(i));
                    }
                }
            }
        }
    }
}
