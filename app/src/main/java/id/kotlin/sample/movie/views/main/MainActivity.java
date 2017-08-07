package id.kotlin.sample.movie.views.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import javax.inject.Inject;

import id.kotlin.sample.movie.R;
import id.kotlin.sample.movie.data.remote.response.DiscoverMovieResponse;
import id.kotlin.sample.movie.deps.provider.ActivityProvider;
import id.kotlin.sample.movie.service.DiscoverMovieService;
import id.kotlin.sample.movie.service.NetworkCallback;
import id.kotlin.sample.movie.utils.Constants;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    protected DiscoverMovieService discoverMovieService;

    private CompositeDisposable disposables;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((ActivityProvider) getApplication()).provideActivityComponent().inject(this);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        disposables = new CompositeDisposable();
        discoverMovie();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }

    private void discoverMovie() {

        final Disposable disposable = discoverMovieService.discoverMovie(Constants.API_KEY,
                                                                         Constants.DEFAULT_SORT,
         new NetworkCallback<DiscoverMovieResponse>() {
             @Override
             public void onSuccess(final DiscoverMovieResponse response) {
                 Log.d(TAG, "Result " + response.page);
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
}