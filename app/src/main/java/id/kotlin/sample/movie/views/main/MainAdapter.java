package id.kotlin.sample.movie.views.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.kotlin.sample.movie.R;
import id.kotlin.sample.movie.data.remote.response.DiscoverMovieResponse;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {

    private final List<DiscoverMovieResponse.Result> results;

    public MainAdapter(final List<DiscoverMovieResponse.Result> results) {
        this.results = results;
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
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class MainHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView desc;

        public MainHolder(final View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tv_title);
            desc = itemView.findViewById(R.id.tv_desc);
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getDesc() {
            return desc;
        }
    }
}