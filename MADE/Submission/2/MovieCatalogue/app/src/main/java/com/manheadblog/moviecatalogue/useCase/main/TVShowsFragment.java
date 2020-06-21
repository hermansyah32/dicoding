package com.manheadblog.moviecatalogue.useCase.main;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
public class TVShowsFragment extends Fragment implements TVShowAdapter.OnItemClickCallaback {

    private String[] tvshowtitle, tvshowdescription, tvshowscore, tvshowstatus, tvshowlanguage,
            tvshowruntime, tvshowgenre;
    private TypedArray tvshowposter;
    private ArrayList<TVShow> tvShowArrayList;

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

        RecyclerView recyclerView = view.findViewById(R.id.recyclerVewTVShows);
        recyclerView.setHasFixedSize(true);
        prepareData();
        addData();

        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        TVShowAdapter tvShowAdapter = new TVShowAdapter(tvShowArrayList);
        recyclerView.setAdapter(tvShowAdapter);
        tvShowAdapter.setOnItemClickCallaback(this);
    }

    private void prepareData() {
        tvshowtitle = getResources().getStringArray(R.array.tvshows_title);
        tvshowdescription = getResources().getStringArray(R.array.tvshows_description);
        tvshowstatus = getResources().getStringArray(R.array.tvshows_status);
        tvshowscore = getResources().getStringArray(R.array.tvshows_score);
        tvshowlanguage = getResources().getStringArray(R.array.tvshows_language);
        tvshowruntime = getResources().getStringArray(R.array.tvshows_runtime);
        tvshowgenre = getResources().getStringArray(R.array.tvshows_genre);
        tvshowposter = getResources().obtainTypedArray(R.array.tvshows_drawable);
    }

    private void addData() {
        tvShowArrayList = new ArrayList<>();

        for (int i = 0; i < tvshowtitle.length; i++) {
            TVShow tvShow = new TVShow();
            tvShow.setTitle(tvshowtitle[i]);
            tvShow.setDescription(tvshowdescription[i]);
            tvShow.setStatus(tvshowstatus[i]);
            tvShow.setScore(Integer.parseInt(tvshowscore[i]));
            tvShow.setOriginal_language(tvshowlanguage[i]);
            tvShow.setRuntime(tvshowruntime[i]);
            tvShow.setGenre(tvshowgenre[i]);
            tvShow.setPoster(tvshowposter.getResourceId(i, -1));
            tvShowArrayList.add(tvShow);
        }
        tvshowposter.recycle();
    }

    @Override
    public void OnItemClicked(TVShow data) {
        Intent intent = new Intent(getActivity(), TVShowDetailActivity.class);
        intent.putExtra(TVShowDetailActivity.TVSHOW_DATA, data);
        startActivity(intent);
    }
}
