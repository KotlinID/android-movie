package id.kotlin.sample.movie.deps.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import id.kotlin.sample.movie.data.remote.Api;
import id.kotlin.sample.movie.service.DiscoverMovieService;

@Module
public class ServiceModule {

    @Provides
    @Singleton
    protected DiscoverMovieService provideDiscoverMovieService(final Api api) {
        return new DiscoverMovieService(api);
    }
}