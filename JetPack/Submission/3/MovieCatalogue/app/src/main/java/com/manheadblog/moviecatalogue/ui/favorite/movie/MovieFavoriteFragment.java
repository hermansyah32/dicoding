package com.manheadblog.moviecatalogue.ui.favorite.movie;

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
import com.manheadblog.moviecatalogue.adapter.OnMovieItemClickCallback;
import com.manheadblog.moviecatalogue.data.resource.Resource;
import com.manheadblog.moviecatalogue.databinding.MovieFavoriteFragmentBinding;
import com.manheadblog.moviecatalogue.entity.Movie;
import com.manheadblog.moviecatalogue.ui.detail.movie.MovieDetailActivity;
import com.manheadblog.moviecatalogue.utils.SimpleDialog;
import com.manheadblog.moviecatalogue.utils.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class MovieFavoriteFragment extends Fragment implements OnMovieItemClickCallback {

    private MovieFavoriteViewModel mViewModel;
    private MovieFavoriteFragmentBinding binding;
    private MovieFavoriteAdapter adapter;

    public MovieFavoriteFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_favorite_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            mViewModel = obtainViewModel(getActivity());
            adapter = new MovieFavoriteAdapter(new ArrayList<Movie>(), getContext());
            adapter.setOnItemClickCallback(this);

            binding.setLifecycleOwner(this);
            binding.recyclerViewMovie.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
            binding.recyclerViewMovie.setAdapter(adapter);

            mViewModel.moviesPaged().observe(this, successPage);
            binding.setViewModel(mViewModel);
        }
    }

    private Observer<Resource<List<Movie>>> success = new Observer<Resource<List<Movie>>>() {
        @Override
        public void onChanged(Resource<List<Movie>> response) {
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

    private Observer<Resource<PagedList<Movie>>> successPage = new Observer<Resource<PagedList<Movie>>>() {
        @Override
        public void onChanged(Resource<PagedList<Movie>> response) {
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
    public void onItemClicked(Movie item) {
        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.DATA, item);
        startActivity(intent);
    }

    @Override
    public void onItemFavorited(Movie item) {
        if (item.isFavorite()){
            SimpleDialog simpleDialog = new SimpleDialog(getContext(), SimpleDialog.DialogType.YES_NO);
            simpleDialog.setMessage(getResources().getString(R.string.dialog_remove_favorite));
            simpleDialog.setCancelable(true);
            simpleDialog.setOnPosiviteButtonListener((dialog, which) -> {
                mViewModel.removeFavorite(item);
                showSnackbarMessage(getResources().getString(R.string.favorite_true));
            });
            simpleDialog.show();
        }
    }

    private void showSnackbarMessage(String message) {
        Snackbar.make(binding.recyclerViewMovie, message, Snackbar.LENGTH_SHORT).show();
    }

    @NonNull
    private static MovieFavoriteViewModel obtainViewModel(FragmentActivity activity){
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(MovieFavoriteViewModel.class);
    }
}
