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

@Entity(tableName = "tvshows")
public class TVShow implements Parcelable {

    @ColumnInfo(name = "first_air_date")
    @SerializedName("first_air_date")
    private String firstAirDate;

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    private String overview;

    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    private String originalLanguage;

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    private String posterPath;

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    private String backdropPath;

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    private double voteAverage;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String name;

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "isFavorite", defaultValue = "0")
    private boolean isFavorite;

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

    public String getStringOriginalLanguage() {
        return new Locale(this.originalLanguage).getDisplayLanguage();
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

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getStringVoteAverage(){
        return String.valueOf(voteAverage);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
                "TVShow{" +
                        "first_air_date = '" + firstAirDate + '\'' +
                        ",overview = '" + overview + '\'' +
                        ",original_language = '" + originalLanguage + '\'' +
                        ",poster_path = '" + posterPath + '\'' +
                        ",backdrop_path = '" + backdropPath + '\'' +
                        ",vote_average = '" + voteAverage + '\'' +
                        ",name = '" + name + '\'' +
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
        dest.writeString(this.firstAirDate);
        dest.writeString(this.overview);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.posterPath);
        dest.writeString(this.backdropPath);
        dest.writeDouble(this.voteAverage);
        dest.writeString(this.name);
        dest.writeInt(this.id);
        dest.writeInt(this.isFavorite ? 1 : 0);
    }

    public TVShow() {
    }

    protected TVShow(Parcel in) {
        this.firstAirDate = in.readString();
        this.overview = in.readString();
        this.originalLanguage = in.readString();
        this.posterPath = in.readString();
        this.backdropPath = in.readString();
        this.voteAverage = in.readDouble();
        this.name = in.readString();
        this.id = in.readInt();
        this.isFavorite = in.readInt() != 0;
    }

    public static final Creator<TVShow> CREATOR = new Creator<TVShow>() {
        @Override
        public TVShow createFromParcel(Parcel source) {
            return new TVShow(source);
        }

        @Override
        public TVShow[] newArray(int size) {
            return new TVShow[size];
        }
    };

    @BindingAdapter("tvshow_poster")
    public static void loadImage(ImageView view, String urlPath){
        if (urlPath != null){
            Glide.with(view.getContext())
                    .load(urlPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(view);
        }
    }

    @BindingAdapter("tvshow_backposter")
    public static void loadImageBackground(ImageView view, String urlPath){
        if (urlPath != null){
            Glide.with(view.getContext())
                    .load(urlPath)
                    .apply(bitmapTransform(new BlurTransformation(5)))
                    .into(view);
        }
    }

    @BindingAdapter("tvshow_favorite")
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