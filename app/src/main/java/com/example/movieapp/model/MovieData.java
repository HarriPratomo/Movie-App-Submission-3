package com.example.movieapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class MovieData implements Parcelable {

    private int id;
    private String title_movie;
    private String date_release_movie;
    private String popularity;
    private String image_movie;
    private String overview;

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    private double rating_movie;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle_movie() {
        return title_movie;
    }

    public void setTitle_movie(String title_movie) {
        this.title_movie = title_movie;
    }

    public String getDate_release_movie() {
        return date_release_movie;
    }

    public void setDate_release_movie(String date_release_movie) {
        this.date_release_movie = date_release_movie;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getImage_movie() {
        return image_movie;
    }

    public void setImage_movie(String image_movie) {
        this.image_movie = image_movie;
    }

    public double getRating_movie() {
        return rating_movie;
    }

    public void setRating_movie(double rating_movie) {
        this.rating_movie = rating_movie;
    }

    public MovieData(JSONObject object) {

        try {
            int id = object.getInt("id");
            String title_movie = object.getString("title");
            String date_released_movie = object.getString("release_date");
            String popularity = object.getString("popularity");
            String image_movie = object.getString("poster_path");
            String overview = object.getString("overview");
            double rating_movie = object.getDouble("vote_average");
            this.id = id;
            this.title_movie = title_movie;
            this.date_release_movie = date_released_movie;
            this.popularity = popularity;
            this.image_movie = image_movie;
            this.rating_movie = rating_movie;
            this.overview = overview;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title_movie);
        dest.writeString(this.date_release_movie);
        dest.writeString(this.popularity);
        dest.writeString(this.image_movie);
        dest.writeString(this.overview);
        dest.writeDouble(this.rating_movie);
    }

    protected MovieData(Parcel in) {
        this.id = in.readInt();
        this.title_movie = in.readString();
        this.date_release_movie = in.readString();
        this.popularity = in.readString();
        this.image_movie = in.readString();
        this.overview = in.readString();
        this.rating_movie = in.readDouble();
    }

    public static final Creator<MovieData> CREATOR = new Creator<MovieData>() {
        @Override
        public MovieData createFromParcel(Parcel source) {
            return new MovieData(source);
        }

        @Override
        public MovieData[] newArray(int size) {
            return new MovieData[size];
        }
    };
}

