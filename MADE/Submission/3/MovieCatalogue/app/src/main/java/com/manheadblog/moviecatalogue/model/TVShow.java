package com.manheadblog.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TVShow implements Parcelable {

    @SerializedName("first_air_date")
    private String firstAirDate;

    @SerializedName("overview")
    private String overview;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("number_of_episodes")
    private int numberOfEpisodes;

    @SerializedName("type")
    private String type;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("genres")
    private List<GenresItem> genres;

    @SerializedName("original_name")
    private String originalName;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("name")
    private String name;

    @SerializedName("episode_run_time")
    private List<Integer> episodeRunTime;

    @SerializedName("id")
    private int id;

    @SerializedName("number_of_seasons")
    private int numberOfSeasons;

    @SerializedName("last_air_date")
    private String lastAirDate;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("homepage")
    private String homepage;

    @SerializedName("status")
    private String status;

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOverview() {
        return overview;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getPosterPath() {
        return "https://image.tmdb.org/t/p/w185" + posterPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getBackdropPath() {
        return "https://image.tmdb.org/t/p/w185" + backdropPath;
    }

    public void setGenres(List<GenresItem> genres) {
        this.genres = genres;
    }

    public List<GenresItem> getGenres() {
        return genres;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEpisodeRunTime(List<Integer> episodeRunTime) {
        this.episodeRunTime = episodeRunTime;
    }

    public List<Integer> getEpisodeRunTime() {
        return episodeRunTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return
                "TVShow{" +
                        "first_air_date = '" + firstAirDate + '\'' +
                        ",overview = '" + overview + '\'' +
                        ",original_language = '" + originalLanguage + '\'' +
                        ",number_of_episodes = '" + numberOfEpisodes + '\'' +
                        ",type = '" + type + '\'' +
                        ",poster_path = '" + posterPath + '\'' +
                        ",backdrop_path = '" + backdropPath + '\'' +
                        ",genres = '" + genres + '\'' +
                        ",original_name = '" + originalName + '\'' +
                        ",popularity = '" + popularity + '\'' +
                        ",vote_average = '" + voteAverage + '\'' +
                        ",name = '" + name + '\'' +
                        ",episode_run_time = '" + episodeRunTime + '\'' +
                        ",id = '" + id + '\'' +
                        ",number_of_seasons = '" + numberOfSeasons + '\'' +
                        ",last_air_date = '" + lastAirDate + '\'' +
                        ",vote_count = '" + voteCount + '\'' +
                        ",homepage = '" + homepage + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.firstAirDate);
        dest.writeString(this.overview);
        dest.writeString(this.originalLanguage);
        dest.writeInt(this.numberOfEpisodes);
        dest.writeString(this.type);
        dest.writeString(this.posterPath);
        dest.writeString(this.backdropPath);
        dest.writeTypedList(this.genres);
        dest.writeString(this.originalName);
        dest.writeDouble(this.popularity);
        dest.writeDouble(this.voteAverage);
        dest.writeString(this.name);
        dest.writeList(this.episodeRunTime);
        dest.writeInt(this.id);
        dest.writeInt(this.numberOfSeasons);
        dest.writeString(this.lastAirDate);
        dest.writeInt(this.voteCount);
        dest.writeString(this.homepage);
        dest.writeString(this.status);
    }

    public TVShow() {
    }

    protected TVShow(Parcel in) {
        this.firstAirDate = in.readString();
        this.overview = in.readString();
        this.originalLanguage = in.readString();
        this.numberOfEpisodes = in.readInt();
        this.type = in.readString();
        this.posterPath = in.readString();
        this.backdropPath = in.readString();
        this.genres = in.createTypedArrayList(GenresItem.CREATOR);
        this.originalName = in.readString();
        this.popularity = in.readDouble();
        this.voteAverage = in.readDouble();
        this.name = in.readString();
        this.episodeRunTime = new ArrayList<Integer>();
        in.readList(this.episodeRunTime, Integer.class.getClassLoader());
        this.id = in.readInt();
        this.numberOfSeasons = in.readInt();
        this.lastAirDate = in.readString();
        this.voteCount = in.readInt();
        this.homepage = in.readString();
        this.status = in.readString();
    }

    public static final Parcelable.Creator<TVShow> CREATOR = new Parcelable.Creator<TVShow>() {
        @Override
        public TVShow createFromParcel(Parcel source) {
            return new TVShow(source);
        }

        @Override
        public TVShow[] newArray(int size) {
            return new TVShow[size];
        }
    };
}