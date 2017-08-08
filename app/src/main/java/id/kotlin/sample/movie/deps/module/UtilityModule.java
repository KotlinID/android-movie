package id.kotlin.sample.movie.deps.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import id.kotlin.sample.movie.utils.Commons;

@Module
public class UtilityModule {

    @Provides
    @Singleton
    protected Commons provideCommons() {
        return new Commons();
    }
}
