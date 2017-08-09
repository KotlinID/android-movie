@file:JvmName("Commons")
package id.kotlin.sample.movie.ext

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.MemoryCategory
import com.bumptech.glide.load.engine.DiskCacheStrategy
import id.kotlin.sample.movie.utils.GlideApp
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

const val BASE_URL = "https://api.themoviedb.org/"
const val BASE_MOVIE_URL = "https://image.tmdb.org/t/p/w185_and_h278_bestv2"
const val API_KEY = "37dc2d42ed324228aa382f0554ad1b28"
const val DEFAULT_SORT = "popularity.desc"
const val DEFAULT_DATE = "dd MMMM yyyy"

internal fun getDate(date: String): String {
    val format = DateTimeFormat.forPattern(DEFAULT_DATE)
    return DateTime(date).toString(format)
}

internal fun loadImage(context: Context,
                       url: String,
                       imageView: ImageView) {
    setMemoryCategory(context)
    GlideApp.with(context)
            .load(url)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(imageView)
}

private fun setMemoryCategory(context: Context) {
    Glide.get(context).setMemoryCategory(MemoryCategory.HIGH)
}