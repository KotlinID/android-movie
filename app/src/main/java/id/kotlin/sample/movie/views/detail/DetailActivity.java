package id.kotlin.sample.movie.views.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import id.kotlin.sample.movie.R;
import id.kotlin.sample.movie.data.local.Movie;
import id.kotlin.sample.movie.deps.provider.ActivityProvider;
import id.kotlin.sample.movie.ext.Commons;

public class DetailActivity extends AppCompatActivity {

    private ImageView ivDetail;
    private TextView tvDetailTitle;
    private TextView tvDetailDesc;
    private TextView tvDetailDate;
    private TextView tvDetailRating;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ((ActivityProvider) getApplication()).provideActivityComponent().inject(this);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.tb_detail);
        toolbar.setTitle(getTitle());
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.bg_arrow_back));
        setSupportActionBar(toolbar);

        ivDetail = (ImageView) findViewById(R.id.iv_detail);
        tvDetailTitle = (TextView) findViewById(R.id.tv_detail_title_value);
        tvDetailDesc = (TextView) findViewById(R.id.tv_detail_desc_value);
        tvDetailDate = (TextView) findViewById(R.id.tv_detail_date_value);
        tvDetailRating = (TextView) findViewById(R.id.tv_detail_rating_value);
        setMovieDetail();
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void setMovieDetail() {
        final Movie movie = getIntent().getParcelableExtra("DETAIL");
        final String title = movie.title;
        final String desc = movie.desc;
        final String date = Commons.getDate(movie.date);
        final String image = Commons.BASE_MOVIE_URL.concat(movie.image);
        final double vote = movie.vote;

        tvDetailTitle.setText(title);
        tvDetailDesc.setText(desc);
        tvDetailDate.setText(date);
        tvDetailRating.setText(String.valueOf(vote));

        Commons.loadImage(this, image, ivDetail);
    }
}