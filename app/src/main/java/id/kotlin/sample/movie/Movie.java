package id.kotlin.sample.movie;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import id.kotlin.sample.movie.deps.component.ActivityComponent;
import id.kotlin.sample.movie.deps.component.ApplicationComponent;
import id.kotlin.sample.movie.deps.component.DaggerApplicationComponent;
import id.kotlin.sample.movie.deps.module.NetworkModule;
import id.kotlin.sample.movie.deps.module.ServiceModule;
import id.kotlin.sample.movie.deps.module.UtilityModule;
import id.kotlin.sample.movie.deps.provider.ActivityProvider;

public class Movie extends Application implements ActivityProvider {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                                              .networkModule(new NetworkModule(this))
                                              .serviceModule(new ServiceModule())
                                              .utilityModule(new UtilityModule())
                                              .build();
    }

    @Override
    protected void attachBaseContext(final Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public ActivityComponent provideActivityComponent() {
        return component;
    }
}