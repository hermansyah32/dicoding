package com.manheadblog.moviecatalogue.ui.movie;

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
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.adapter.OnMovieItemClickCallback;
import com.manheadblog.moviecatalogue.data.resource.Resource;
import com.manheadblog.moviecatalogue.databinding.MovieDiscoverFragmentBinding;
import com.manheadblog.moviecatalogue.entity.Movie;
import com.manheadblog.moviecatalogue.ui.detail.movie.MovieDetailActivity;
import com.manheadblog.moviecatalogue.utils.SimpleDialog;
import com.manheadblog.moviecatalogue.utils.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class MovieDiscoverFragment extends Fragment implements OnMovieItemClickCallback {

    private MovieDiscoverViewModel mViewModel;
    private MovieDiscoverFragmentBinding binding;
    private MovieDiscoverAdapter adapter;

    public MovieDiscoverFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_discover_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            mViewModel = obtainViewModel(getActivity());
            adapter = new MovieDiscoverAdapter(new ArrayList<Movie>(), getContext());
            adapter.setOnItemClickCallback(this);
            binding.setLifecycleOwner(this);
            binding.recyclerViewMovie.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
            binding.recyclerViewMovie.setAdapter(adapter);

            mViewModel.setPage(1);
            mViewModel.movies.observe(this, success);
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
                    case SUCCESS_DB:
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
                showSnackbarMessage(getResources().getString(R.string.favorite_false));
            });
            simpleDialog.show();
        }else {
            mViewModel.setFavorite(item);
            showSnackbarMessage(getResources().getString(R.string.favorite_true));
        }
    }

    private void showSnackbarMessage(String message) {
        Snackbar.make(binding.recyclerViewMovie, message, Snackbar.LENGTH_SHORT).show();
    }

    @NonNull
    private static MovieDiscoverViewModel obtainViewModel(FragmentActivity activity){
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(MovieDiscoverViewModel.class);
    }
}
