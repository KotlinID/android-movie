package id.kotlin.sample.movie.service;

import com.bumptech.glide.load.HttpException;

import org.reactivestreams.Publisher;

import id.kotlin.sample.movie.data.remote.Api;
import id.kotlin.sample.movie.data.remote.response.DiscoverMovieResponse;
import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.subscribers.DisposableSubscriber;

public class DiscoverMovieService {

    private final Api api;

    public DiscoverMovieService(final Api api) {
        this.api = api;
    }

    public Disposable discoverMovie(final String key,
                                    final String sortBy,
                                    final NetworkCallback<DiscoverMovieResponse> callback) {
        return api.discoverMovie(key, sortBy)
                  .compose(new NetworkCallTransformer<DiscoverMovieResponse>())
                  .onErrorResumeNext(new Function<Throwable, Publisher<? extends DiscoverMovieResponse>>() {
                      @Override
                      public Publisher<? extends DiscoverMovieResponse> apply(@NonNull final Throwable throwable) throws Exception {
                          return Flowable.error(throwable);
                      }
                  })
                  .subscribeWith(new DisposableSubscriber<DiscoverMovieResponse>() {
                      @Override
                      public void onNext(final DiscoverMovieResponse response) {
                          callback.onSuccess(response);
                      }

                      @Override
                      public void onError(final Throwable throwable) {
                          if (throwable instanceof HttpException) {
                              callback.onError(throwable);
                          } else {
                              callback.onNetworkError(throwable);
                          }
                      }

                      @Override
                      public void onComplete() {
                      }
                  });
    }
}