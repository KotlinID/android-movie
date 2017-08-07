package id.kotlin.sample.movie.data.remote;

import id.kotlin.sample.movie.data.remote.response.DiscoverMovieResponse;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("3/discover/movie")
    Flowable<DiscoverMovieResponse> discoverMovie(@Query("api_key") final String key,
                                                  @Query("sort_by") final String sortBy);
}