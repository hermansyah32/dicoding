package com.manheadblog.moviecatalogue.data.remote;

import android.os.Parcel;
import android.os.Parcelable;

import com.manheadblog.moviecatalogue.entity.TVShow;

import java.util.ArrayList;

public class TVShowPagingResponse implements Parcelable {

    private int page;
    private int total_pages;
    private int total_results;
    private ArrayList<TVShow> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public ArrayList<TVShow> getResults() {
        return results;
    }

    public void setResults(ArrayList<TVShow> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.page);
        dest.writeInt(this.total_pages);
        dest.writeInt(this.total_results);
        dest.writeTypedList(this.results);
    }

    public TVShowPagingResponse() {
    }

    protected TVShowPagingResponse(Parcel in) {
        this.page = in.readInt();
        this.total_pages = in.readInt();
        this.total_results = in.readInt();
        this.results = in.createTypedArrayList(TVShow.CREATOR);
    }

    public static final Parcelable.Creator<TVShowPagingResponse> CREATOR = new Parcelable.Creator<TVShowPagingResponse>() {
        @Override
        public TVShowPagingResponse createFromParcel(Parcel source) {
            return new TVShowPagingResponse(source);
        }

        @Override
        public TVShowPagingResponse[] newArray(int size) {
            return new TVShowPagingResponse[size];
        }
    };
}
