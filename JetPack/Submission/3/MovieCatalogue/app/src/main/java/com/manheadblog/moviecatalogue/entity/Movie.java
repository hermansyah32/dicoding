package com.manheadblog.moviecatalogue.entity;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.SerializedName;
import com.manheadblog.moviecatalogue.App;
import com.manheadblog.moviecatalogue.R;

import java.util.Locale;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

@Entity (tableName = "movies")
public class Movie implements Parcelable {

    @ColumnInfo (name = "overview")
    @SerializedName("overview")
    private String overview;

    @ColumnInfo (name = "original_language")
    @SerializedName("original_language")
    private String originalLanguage;

    @ColumnInfo (name = "title")
    @SerializedName("title")
    private String title;

    @ColumnInfo (name = "poster_path")
    @SerializedName("poster_path")
    private String posterPath;

    @ColumnInfo (name = "backdrop_path")
    @SerializedName("backdrop_path")
    private String backdropPath;

    @ColumnInfo (name = "release_date")
    @SerializedName("release_date")
    private String releaseDate;

    @ColumnInfo (name = "vote_average")
    @SerializedName("vote_average")
    private double voteAverage;

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "isFavorite", defaultValue = "0")
    private boolean isFavorite;

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

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public String toString() {
        return
                "Movie{" +
                        "overview = '" + overview + '\'' +
                        ",original_language = '" + originalLanguage + '\'' +
                        ",title = '" + title + '\'' +
                        ",poster_path = '" + posterPath + '\'' +
                        ",backdrop_path = '" + backdropPath + '\'' +
                        ",release_date = '" + releaseDate + '\'' +
                        ",vote_average = '" + voteAverage + '\'' +
                        ",id = '" + id + '\'' +
                        ",isFavorite = '" + isFavorite + '\'' +
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
        dest.writeString(this.title);
        dest.writeString(this.posterPath);
        dest.writeString(this.backdropPath);
        dest.writeString(this.releaseDate);
        dest.writeDouble(this.voteAverage);
        dest.writeInt(this.id);
        dest.writeInt(this.isFavorite ? 1 : 0);
    }

    public Movie() {
    }



    protected Movie(Parcel in) {
        this.overview = in.readString();
        this.originalLanguage = in.readString();
        this.title = in.readString();
        this.posterPath = in.readString();
        this.backdropPath = in.readString();
        this.releaseDate = in.readString();
        this.voteAverage = in.readDouble();
        this.id = in.readInt();
        this.isFavorite = in.readInt() != 0;
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

    @BindingAdapter("movie_favorite")
    public static void refreshImageFavorite(ImageView view, boolean favorite_state){
        if (favorite_state){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1){
                Glide.with(view.getContext())
                        .load(App.getInstance().getResources().getDrawable(R.drawable.ic_favorite_true))
                        .apply(bitmapTransform(new BlurTransformation(5)))
                        .into(view);
            }else {
                Glide.with(view.getContext())
                        .load(App.getInstance().getDrawable(R.drawable.ic_favorite_true))
                        .apply(bitmapTransform(new BlurTransformation(5)))
                        .into(view);
            }
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1){
                Glide.with(view.getContext())
                        .load(App.getInstance().getResources().getDrawable(R.drawable.ic_favorite_false))
                        .apply(bitmapTransform(new BlurTransformation(5)))
                        .into(view);
            }else {
                Glide.with(view.getContext())
                        .load(App.getInstance().getDrawable(R.drawable.ic_favorite_false))
                        .apply(bitmapTransform(new BlurTransformation(5)))
                        .into(view);
            }
        }
    }
}