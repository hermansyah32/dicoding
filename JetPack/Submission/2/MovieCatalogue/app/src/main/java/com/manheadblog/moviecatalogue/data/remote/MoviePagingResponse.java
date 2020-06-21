package com.manheadblog.moviecatalogue.data.remote;

import android.os.Parcel;
import android.os.Parcelable;

import com.manheadblog.moviecatalogue.entity.Movie;

import java.util.ArrayList;

public class MoviePagingResponse implements Parcelable {
    private int page;
    private int total_pages;
    private int total_results;
    private ArrayList<Movie> results;

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

    public ArrayList<Movie> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movie> results) {
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

    public MoviePagingResponse() {
    }

    protected MoviePagingResponse(Parcel in) {
        this.page = in.readInt();
        this.total_pages = in.readInt();
        this.total_results = in.readInt();
        this.results = in.createTypedArrayList(Movie.CREATOR);
    }

    public static final Parcelable.Creator<MoviePagingResponse> CREATOR = new Parcelable.Creator<MoviePagingResponse>() {
        @Override
        public MoviePagingResponse createFromParcel(Parcel source) {
            return new MoviePagingResponse(source);
        }

        @Override
        public MoviePagingResponse[] newArray(int size) {
            return new MoviePagingResponse[size];
        }
    };
}
