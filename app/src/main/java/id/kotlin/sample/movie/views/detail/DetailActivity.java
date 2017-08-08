package id.kotlin.sample.movie.views.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import id.kotlin.sample.movie.R;
import id.kotlin.sample.movie.data.local.Movie;
import id.kotlin.sample.movie.deps.provider.ActivityProvider;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

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

        final Movie movie = getIntent().getParcelableExtra("DETAIL");
        final String title = movie.title;
        Log.d(TAG, "TITLE " + title);
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
}