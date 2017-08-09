package id.kotlin.sample.movie.deps.component;

import javax.inject.Singleton;

import dagger.Component;
import id.kotlin.sample.movie.deps.module.NetworkModule;
import id.kotlin.sample.movie.deps.module.ServiceModule;

@Singleton
@Component(modules = {
        NetworkModule.class,
        ServiceModule.class
})
public interface ApplicationComponent extends ActivityComponent {
}