package id.kotlin.sample.movie.deps.provider;

import id.kotlin.sample.movie.deps.component.ActivityComponent;

public interface ActivityProvider {

    ActivityComponent provideActivityComponent();
}