package com.manheadblog.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TVShow implements Parcelable {
    private String title, description, status,
            original_language, runtime, genre;
    private int score, poster;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.status);
        dest.writeString(this.original_language);
        dest.writeString(this.runtime);
        dest.writeString(this.genre);
        dest.writeInt(this.score);
        dest.writeInt(this.poster);
    }

    public TVShow() {
    }

    protected TVShow(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.status = in.readString();
        this.original_language = in.readString();
        this.runtime = in.readString();
        this.genre = in.readString();
        this.score = in.readInt();
        this.poster = in.readInt();
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
}
