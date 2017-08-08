package id.kotlin.sample.movie.deps.component;

import dagger.Subcomponent;
import id.kotlin.sample.movie.deps.scope.ActivityScope;
import id.kotlin.sample.movie.views.detail.DetailActivity;
import id.kotlin.sample.movie.views.main.MainActivity;

@ActivityScope
@Subcomponent
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(DetailActivity detailActivity);
}