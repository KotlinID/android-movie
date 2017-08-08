package id.kotlin.sample.movie.data.local;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    public String title;
    public String desc;
    public String date;
    public String image;
    public double vote;

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

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.title = in.readString();
        this.desc = in.readString();
        this.date = in.readString();
        this.image = in.readString();
        this.vote = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.desc);
        dest.writeString(this.date);
        dest.writeString(this.image);
        dest.writeDouble(this.vote);
    }
}