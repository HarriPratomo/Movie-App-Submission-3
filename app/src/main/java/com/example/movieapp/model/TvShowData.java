package com.example.movieapp.model;


import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONObject;

public class TvShowData implements Parcelable {
    private int id;
    private String name;
    private String date;
    private String overview;
    private String poster_path;
    private String popularity;
    private Double rating_tv;

    public Double getRating_tv() {
        return rating_tv;
    }

    public void setRating_tv(Double rating_tv) {
        this.rating_tv = rating_tv;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public TvShowData(JSONObject object) {

        try {
            int id = object.getInt("id");
            String name = object.getString("name");
            String overview = object.getString("overview");
            String date = object.getString("first_air_date");
            String poster_path = object.getString("poster_path");
            String popularity = object.getString("popularity");
            double rating_tv = object.getDouble("vote_average");
            this.id = id;
            this.name = name;
            this.date = date;
            this.popularity = popularity;
            this.poster_path = poster_path;
            this.rating_tv = rating_tv;
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
        dest.writeString(this.name);
        dest.writeString(this.date);
        dest.writeString(this.overview);
        dest.writeString(this.poster_path);
        dest.writeString(this.popularity);
        dest.writeValue(this.rating_tv);
    }

    protected TvShowData(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.date = in.readString();
        this.overview = in.readString();
        this.poster_path = in.readString();
        this.popularity = in.readString();
        this.rating_tv = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Creator<TvShowData> CREATOR = new Creator<TvShowData>() {
        @Override
        public TvShowData createFromParcel(Parcel source) {
            return new TvShowData(source);
        }

        @Override
        public TvShowData[] newArray(int size) {
            return new TvShowData[size];
        }
    };
}
