package id.kotlin.sample.movie.service;

public interface NetworkCallback<T> {

    void onSuccess(T response);

    void onError(Throwable e);

    void onNetworkError(Throwable e);
}