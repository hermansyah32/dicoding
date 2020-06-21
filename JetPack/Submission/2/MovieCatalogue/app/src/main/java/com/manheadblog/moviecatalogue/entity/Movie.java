package com.manheadblog.moviecatalogue.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.SerializedName;
import com.manheadblog.moviecatalogue.App;
import com.manheadblog.moviecatalogue.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class Movie implements Parcelable {

    @SerializedName("overview")
    private String overview;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("runtime")
    private int runtime;

    @SerializedName("title")
    private String title;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("revenue")
    private int revenue;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("genres")
    private List<GenresItem> genres;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("tagline")
    private String tagline;

    @SerializedName("id")
    private int id;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("budget")
    private int budget;

    @SerializedName("status")
    private String status;

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

    public String getStringOriginalLanguage() {
        return new Locale(originalLanguage).getDisplayLanguage();
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getStringRuntime(){
        String result = "";
        int hour = runtime / 60;
        int minute = runtime % 60;
        if (runtime<1){
            result = "";
        }else if (runtime<60){
            result = minute +
                    App.getInstance().getResources().getString(R.string.minute);
        }else {
            result = hour +
                    App.getInstance().getResources().getString(R.string.hour) + " " +
                    minute +
                    App.getInstance().getResources().getString(R.string.minute);
        }
        return result;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getURLPosterPath() {
        return "https://image.tmdb.org/t/p/w185" + posterPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getURLBackdropPath() {
        return "https://image.tmdb.org/t/p/w185" + backdropPath;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getRevenue() {
        return revenue;
    }

    public String getStringRevenue(){
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.CANADA);
        String result = numberFormat.format(budget);
        if (budget < 1) result = "-";
        return result;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setGenres(List<GenresItem> genres) {
        this.genres = genres;
    }

    public List<GenresItem> getGenres() {
        return genres;
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

    public String getStringVoteAverage() {
        return String.valueOf(voteAverage);
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTagline() {
        return tagline;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getBudget() {
        return budget;
    }

    public String getStringBudget(){
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.CANADA);
        String result = numberFormat.format(revenue);
        if (revenue < 1) result = "-";
        return result;
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
                "Movie{" +
                        "overview = '" + overview + '\'' +
                        ",original_language = '" + originalLanguage + '\'' +
                        ",original_title = '" + originalTitle + '\'' +
                        ",runtime = '" + runtime + '\'' +
                        ",title = '" + title + '\'' +
                        ",poster_path = '" + posterPath + '\'' +
                        ",backdrop_path = '" + backdropPath + '\'' +
                        ",revenue = '" + revenue + '\'' +
                        ",release_date = '" + releaseDate + '\'' +
                        ",genres = '" + genres + '\'' +
                        ",popularity = '" + popularity + '\'' +
                        ",vote_average = '" + voteAverage + '\'' +
                        ",tagline = '" + tagline + '\'' +
                        ",id = '" + id + '\'' +
                        ",vote_count = '" + voteCount + '\'' +
                        ",budget = '" + budget + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.overview);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.originalTitle);
        dest.writeInt(this.runtime);
        dest.writeString(this.title);
        dest.writeString(this.posterPath);
        dest.writeString(this.backdropPath);
        dest.writeInt(this.revenue);
        dest.writeString(this.releaseDate);
        dest.writeList(this.genres);
        dest.writeDouble(this.popularity);
        dest.writeDouble(this.voteAverage);
        dest.writeString(this.tagline);
        dest.writeInt(this.id);
        dest.writeInt(this.voteCount);
        dest.writeInt(this.budget);
        dest.writeString(this.status);
    }

    public Movie() {
    }



    protected Movie(Parcel in) {
        this.overview = in.readString();
        this.originalLanguage = in.readString();
        this.originalTitle = in.readString();
        this.runtime = in.readInt();
        this.title = in.readString();
        this.posterPath = in.readString();
        this.backdropPath = in.readString();
        this.revenue = in.readInt();
        this.releaseDate = in.readString();
        this.genres = new ArrayList<GenresItem>();
        in.readList(this.genres, GenresItem.class.getClassLoader());
        this.popularity = in.readDouble();
        this.voteAverage = in.readDouble();
        this.tagline = in.readString();
        this.id = in.readInt();
        this.voteCount = in.readInt();
        this.budget = in.readInt();
        this.status = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @BindingAdapter("movie_poster")
    public static void loadImage(ImageView view, String urlPath){
        if (urlPath != null){
            Glide.with(view.getContext())
                    .load(urlPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(view);
        }
    }

    @BindingAdapter("movie_backposter")
    public static void loadImageBackground(ImageView view, String urlPath){
        if (urlPath != null){
            Glide.with(view.getContext())
                    .load(urlPath)
                    .apply(bitmapTransform(new BlurTransformation(5)))
                    .into(view);
        }
    }
}