package com.manheadblog.moviecatalogue.entity;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.manheadblog.moviecatalogue.App;
import com.manheadblog.moviecatalogue.R;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.bumptech.glide.request.RequestOptions.bitmapTransform;
import static java.lang.Math.round;

public class Movie implements Parcelable {
    private int id;
    private String title;
    private String overview;
    private double score;
    private String language;
    private String release_date;
    private int poster;

    public Movie(int id, String title, String overview, double score, String language, String release_date, int poster) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.score = score;
        this.language = language;
        this.release_date = release_date;
        this.poster = poster;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getScore() {
        return score;
    }

    public String getScoreString(){
        return String.valueOf(round(score));
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    @BindingAdapter("movie_poster")
    public static void loadImage(ImageView view, int drawableid){
        Log.d(TAG, "loadImage: " + drawableid);
        if (drawableid != 0){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1){
                Glide.with(view.getContext())
                        .load(App.getInstance().getResources().getDrawable(drawableid))
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                        .into(view);
            }else {
                Glide.with(view.getContext())
                        .load(App.getInstance().getDrawable(drawableid))
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                        .into(view);
            }
        }
    }

    @BindingAdapter("movie_backposter")
    public static void loadImageBackground(ImageView view, int drawableid){
        if (drawableid != 0){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1){
                Glide.with(view.getContext())
                        .load(App.getInstance().getResources().getDrawable(drawableid))
                        .apply(bitmapTransform(new BlurTransformation(5)))
                        .into(view);
            }else {
                Glide.with(view.getContext())
                        .load(App.getInstance().getDrawable(drawableid))
                        .apply(bitmapTransform(new BlurTransformation(5)))
                        .into(view);
            }
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeDouble(this.score);
        dest.writeString(this.language);
        dest.writeString(this.release_date);
        dest.writeInt(this.poster);
    }

    protected Movie(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.overview = in.readString();
        this.score = in.readDouble();
        this.language = in.readString();
        this.release_date = in.readString();
        this.poster = in.readInt();
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
}
