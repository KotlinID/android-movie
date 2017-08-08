package id.kotlin.sample.movie.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public final class Commons {

    public String getDate(final String date) {
        final DateTimeFormatter format = DateTimeFormat.forPattern(Constants.DEFAULT_DATE);
        return new DateTime(date).toString(format);
    }

    public void loadImage(final Context context,
                          final String url,
                          final ImageView imageView) {
        setMemoryCategory(context);
        GlideApp.with(context)
                .load(url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView);
    }

    private void setMemoryCategory(final Context context) {
        Glide.get(context).setMemoryCategory(MemoryCategory.HIGH);
    }
}