package id.kotlin.sample.movie.views.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import id.kotlin.sample.movie.R;
import id.kotlin.sample.movie.data.local.Movie;
import id.kotlin.sample.movie.data.remote.response.DiscoverMovieResponse;
import id.kotlin.sample.movie.deps.provider.ActivityProvider;
import id.kotlin.sample.movie.ext.Commons;
import id.kotlin.sample.movie.service.DiscoverMovieService;
import id.kotlin.sample.movie.service.NetworkCallback;
import id.kotlin.sample.movie.views.detail.DetailActivity;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    protected DiscoverMovieService discoverMovieService;

    private CompositeDisposable disposables;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((ActivityProvider) getApplication()).provideActivityComponent().inject(this);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.tb_main);
        toolbar.setTitle(getTitle());
        setSupportActionBar(toolbar);

        progressBar = (ProgressBar) findViewById(R.id.pb_main);
        recyclerView = (RecyclerView) findViewById(R.id.rv_main);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        disposables = new CompositeDisposable();
        discoverMovie();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }

    private void discoverMovie() {
        progressBar.setVisibility(View.VISIBLE);

        final Disposable disposable = discoverMovieService.discoverMovie(Commons.API_KEY,
                                                                         Commons.DEFAULT_SORT,
         new NetworkCallback<DiscoverMovieResponse>() {
             @Override
             public void onSuccess(final DiscoverMovieResponse response) {
                 getProgressBar().setVisibility(View.GONE);

                 final List<DiscoverMovieResponse.Result> results = response.results;
                 final MainAdapter adapter =
                         new MainAdapter(results, new MainAdapter.MovieListener() {
                             @Override
                             public void onClick(final Movie movie) {
                                 final Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                                 final Bundle bundle = new Bundle();
                                 bundle.putParcelable("DETAIL", movie);
                                 intent.putExtras(bundle);
                                 startActivity(intent);
                             }
                         });
                 getRecyclerView().setAdapter(adapter);
                 adapter.notifyDataSetChanged();
             }

             @Override
             public void onError(final Throwable e) {
                 Log.e(TAG, e.getMessage(), e);
             }

             @Override
             public void onNetworkError(final Throwable e) {
                 Log.e(TAG, e.getMessage(), e);
             }
         });

        disposables.add(disposable);
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}