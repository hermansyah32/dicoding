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

import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.data.remote.MoviePagingResponse;
import com.manheadblog.moviecatalogue.databinding.MovieDiscoverFragmentBinding;
import com.manheadblog.moviecatalogue.entity.Movie;
import com.manheadblog.moviecatalogue.ui.detail.movie.MovieDetailActivity;
import com.manheadblog.moviecatalogue.utils.ViewModelFactory;

import java.util.ArrayList;

public class MovieDiscoverFragment extends Fragment implements MovieDiscoverAdapter.OnItemClickCallback {

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

            mViewModel.busy.set(true);
            mViewModel.getData(1).observe(this,success);
            binding.setViewModel(mViewModel);
        }
    }


    private Observer<MoviePagingResponse> success = new Observer<MoviePagingResponse>() {
        @Override
        public void onChanged(MoviePagingResponse response) {
            if (response != null){
                mViewModel.busy.set(false);
                adapter.setArrayList(response.getResults());
            }
        }
    };

    @Override
    public void onItemClicked(Movie item) {
        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.DATA, item);
        startActivity(intent);
    }

    @NonNull
    private static MovieDiscoverViewModel obtainViewModel(FragmentActivity activity){
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(MovieDiscoverViewModel.class);
    }
}
