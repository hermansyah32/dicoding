package com.manheadblog.moviecatalogue.ui.detail.movie;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.manheadblog.moviecatalogue.data.RepositoryData;
import com.manheadblog.moviecatalogue.data.resource.Resource;
import com.manheadblog.moviecatalogue.entity.Movie;

public class MovieDetailActivityViewModel extends ViewModel {
    public ObservableField<Boolean> busy = new ObservableField<>();
    private RepositoryData repositoryData;
    private MutableLiveData<Integer> id = new MutableLiveData<>();

    public MovieDetailActivityViewModel(RepositoryData repositoryData) {
        this.repositoryData = repositoryData;
    }

    public void setId(int id){
        this.id.setValue(id);
    }

    public int getId() {
        if (id.getValue() == null) return 0;
        return id.getValue();
    }

    LiveData<Resource<Movie>> movie = Transformations.switchMap(
            id, mId -> repositoryData.getMovie(mId)
    );

}
