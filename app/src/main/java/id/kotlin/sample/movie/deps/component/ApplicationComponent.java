package id.kotlin.sample.movie.deps.component;

import javax.inject.Singleton;

import dagger.Component;
import id.kotlin.sample.movie.deps.module.NetworkModule;
import id.kotlin.sample.movie.deps.module.ServiceModule;
import id.kotlin.sample.movie.deps.module.UtilityModule;

@Singleton
@Component(modules = {
        NetworkModule.class,
        ServiceModule.class,
        UtilityModule.class
})
public interface ApplicationComponent extends ActivityComponent {
}