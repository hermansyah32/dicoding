package com.manheadblog.moviecatalogue.ui.detail.tvshow;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.manheadblog.moviecatalogue.entity.TVShow;
import com.manheadblog.moviecatalogue.utils.DataDummy;

public class TVShowDetailActivityViewModel extends ViewModel {
    public ObservableField<Boolean> busy = new ObservableField<>();
    private MutableLiveData<TVShow> success = new MutableLiveData<>();

    LiveData<TVShow> getSuccess(){
        return this.success;
    }

    public TVShow getData(int id){
        busy.set(true);
        for (int i = 0; i < DataDummy.generateTVShows().size(); i++) {
            TVShow tvShow = DataDummy.generateTVShows().get(i);
            if (tvShow.getId() == id){
                success.postValue(tvShow);
                busy.set(false);
                return tvShow;
            }
        }
        return null;
    }
}
