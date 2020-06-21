package com.manheadblog.moviecatalogue.ui.tvshow;

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
import com.manheadblog.moviecatalogue.data.remote.TVShowPagingResponse;
import com.manheadblog.moviecatalogue.databinding.TvshowDiscoverFragmentBinding;
import com.manheadblog.moviecatalogue.entity.TVShow;
import com.manheadblog.moviecatalogue.ui.detail.tvshow.TVShowDetailActivity;
import com.manheadblog.moviecatalogue.utils.ViewModelFactory;

import java.util.ArrayList;

public class TVShowDiscoverFragment extends Fragment implements TVShowDiscoverAdapter.OnItemClickCallback{

    private TVShowDiscoverViewModel mViewModel;
    private TVShowDiscoverAdapter adapter;
    private TvshowDiscoverFragmentBinding binding;

    public TVShowDiscoverFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tvshow_discover_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            mViewModel = obtainViewModel(getActivity());
            adapter = new TVShowDiscoverAdapter(new ArrayList<TVShow>(), getContext());

            adapter.setOnItemClickCallback(this);
            binding.setLifecycleOwner(this);
            binding.recyclerViewTVShow.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
            binding.recyclerViewTVShow.setAdapter(adapter);

            mViewModel.busy.set(true);
            mViewModel.getData(1).observe(this,success);
            binding.setViewModel(mViewModel);
        }

    }

    private Observer<TVShowPagingResponse> success = new Observer<TVShowPagingResponse>() {
        @Override
        public void onChanged(TVShowPagingResponse response) {
            if (response != null){
                mViewModel.busy.set(false);
                adapter.setArrayList(response.getResults());
            }
        }
    };

    @Override
    public void onItemClicked(TVShow item) {
        Intent intent = new Intent(getContext(), TVShowDetailActivity.class);
        intent.putExtra(TVShowDetailActivity.DATA, item);
        startActivity(intent);
    }

    @NonNull
    private static TVShowDiscoverViewModel obtainViewModel(FragmentActivity activity){
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(TVShowDiscoverViewModel.class);
    }
}
