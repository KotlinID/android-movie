package id.kotlin.sample.movie.views.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import id.kotlin.sample.movie.R;
import id.kotlin.sample.movie.data.local.Movie;
import id.kotlin.sample.movie.data.remote.response.DiscoverMovieResponse;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {

    private final List<DiscoverMovieResponse.Result> results;
    private final MovieListener listener;

    public MainAdapter(final List<DiscoverMovieResponse.Result> results,
                       final MovieListener listener) {
        this.results = results;
        this.listener = listener;
    }

    @Override
    public MainHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new MainHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false));
    }

    @Override
    public void onBindViewHolder(final MainHolder holder, final int position) {
        final DiscoverMovieResponse.Result result = results.get(holder.getAdapterPosition());
        holder.getTitle().setText(result.title);
        holder.getDesc().setText(result.overview);

        holder.getLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final Movie movie = new Movie();
                movie.title = result.title;
                movie.desc = result.overview;
                movie.date = result.releaseDate;
                movie.image = result.posterPath;
                movie.vote = result.voteAverage;

                getListener().onClick(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public MovieListener getListener() {
        return listener;
    }

    class MainHolder extends RecyclerView.ViewHolder {

        private RelativeLayout layout;
        private TextView title;
        private TextView desc;

        public MainHolder(final View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.layout_item_main);
            title = itemView.findViewById(R.id.tv_title);
            desc = itemView.findViewById(R.id.tv_desc);
        }

        public RelativeLayout getLayout() {
            return layout;
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getDesc() {
            return desc;
        }
    }

    interface MovieListener {

        void onClick(Movie movie);
    }
}