package com.manheadblog.moviecatalogue.useCase.release;

import android.content.Intent;
import android.net.Uri;
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
import com.manheadblog.moviecatalogue.adapter.TVShowAdapter;
import com.manheadblog.moviecatalogue.databinding.ReleaseTvshowFragmentBinding;
import com.manheadblog.moviecatalogue.db.TVShowHelper;
import com.manheadblog.moviecatalogue.helper.TVShowMappingHelper;
import com.manheadblog.moviecatalogue.model.TVShow;
import com.manheadblog.moviecatalogue.useCase.tvdetail.TVShowDetailActivity;

import java.util.ArrayList;

import static com.manheadblog.moviecatalogue.db.DatabaseContract.TVShowColumn.CONTENT_URI_TVSHOW;

public class ReleaseTVShowFragment extends Fragment implements TVShowAdapter.OnItemClickCallaback, View.OnClickListener{

    private static final String PAGE = "PAGE";
    private static final String TOTAL_PAGE = "TOTAL_PAGE";
    private static final String LIST_DATA = "LIST_DATA";
    private static final String LIST_DATA_LOCAL = "LIST_DATA_LOCAL";

    public ReleaseTvshowViewModel viewModel;
    private TVShowAdapter tvShowAdapter;
    private TVShowHelper tvShowHelper;
    private ReleaseTvshowFragmentBinding binding;

    public static ReleaseTVShowFragment newInstance() {
        return new ReleaseTVShowFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.release_tvshow_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.buttonLoad.setOnClickListener(this);

        tvShowHelper = TVShowHelper.getInstance();

        viewModel = ViewModelProviders.of(this).get(ReleaseTvshowViewModel.class);
        viewModel.getTVShows().observe(this, getTVShows);

        if (savedInstanceState == null){
            tvShowAdapter = new TVShowAdapter(new ArrayList<TVShow>(), new ArrayList<TVShow>());
            showLoading(true);
            viewModel.setData(1);
        }else {
            viewModel.page = savedInstanceState.getInt(PAGE);
            viewModel.total_pages = savedInstanceState.getInt(TOTAL_PAGE);
            tvShowAdapter = new TVShowAdapter(savedInstanceState.<TVShow>getParcelableArrayList(LIST_DATA),
                    savedInstanceState.<TVShow>getParcelableArrayList(LIST_DATA_LOCAL));
        }

        binding.recyclerVewTVShows.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        binding.recyclerVewTVShows.setAdapter(tvShowAdapter);
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

    @Override
    public void OnItemLiked(boolean isLiked, TVShow data, int position, View itemView) {
        ImageView imageView = itemView.findViewById(R.id.imageViewTVShowFavorite);
        if (!isLiked){
            getContext().getContentResolver().insert(CONTENT_URI_TVSHOW, TVShowMappingHelper.mapObjectToContentValues(data));
            showSnackbarMessage(getResources().getString(R.string.tvshow_liked));
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_black_24dp));
            tvShowAdapter.addItemLocal(data, position);
        }else {
            Uri uri = Uri.parse(CONTENT_URI_TVSHOW + "/" + data.getId());
            getContext().getContentResolver().delete(uri,null,null);
            showSnackbarMessage(getResources().getString(R.string.tvshow_disliked));
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp));
            tvShowAdapter.removeItemLocal(position);
        }
    }

    private void showLoading(Boolean state) {
        if (state) {
            binding.loadingBar.setVisibility(View.VISIBLE);
            binding.buttonLoad.setVisibility(View.GONE);
            binding.recyclerVewTVShows.setEnabled(false);
        } else {
            binding.loadingBar.setVisibility(View.GONE);
            binding.buttonLoad.setVisibility(View.VISIBLE);
            binding.recyclerVewTVShows.setEnabled(false);
        }
    }

    public void refreshList(){
        tvShowAdapter.clearData();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PAGE, viewModel.page);
        outState.putInt(TOTAL_PAGE, viewModel.total_pages);
        outState.putParcelableArrayList(LIST_DATA, tvShowAdapter.getTvShowArrayList());
        outState.putParcelableArrayList(LIST_DATA_LOCAL, tvShowAdapter.getLocalTvShowArrayList());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonLoad){
            viewModel.setData(viewModel.page);
            showLoading(true);
        }
    }

    private void showSnackbarMessage(String message) {
        Snackbar.make(binding.recyclerVewTVShows, message, Snackbar.LENGTH_SHORT).show();
    }
}
