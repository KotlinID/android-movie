package id.kotlin.sample.movie.data.local

import android.os.Parcel
import android.os.Parcelable

data class Movie(@JvmField var title: String,
                 @JvmField var desc: String,
                 @JvmField var date: String,
                 @JvmField var image: String,
                 @JvmField var vote: Double) : Parcelable {

    constructor() : this(
            title = "",
            desc = "",
            date = "",
            image = "",
            vote = 0.0
    )

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Movie> = object : Parcelable.Creator<Movie> {
            override fun createFromParcel(source: Parcel): Movie = Movie(source)
            override fun newArray(size: Int): Array<Movie?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readDouble()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeString(desc)
        dest.writeString(date)
        dest.writeString(image)
        dest.writeDouble(vote)
    }
}