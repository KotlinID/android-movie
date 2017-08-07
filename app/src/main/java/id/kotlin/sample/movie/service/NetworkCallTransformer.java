package id.kotlin.sample.movie.service;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

public class NetworkCallTransformer<T> implements FlowableTransformer<T, T> {

    @Override
    public Publisher<T> apply(@NonNull final Flowable<T> flowable) {
        return flowable.subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread())
                       .unsubscribeOn(Schedulers.io())
                       .cache();
    }
}