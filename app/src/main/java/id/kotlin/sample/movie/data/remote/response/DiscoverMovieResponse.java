package id.kotlin.sample.movie.data.remote.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DiscoverMovieResponse {

    @JsonProperty("page")
    public int page;

    @JsonProperty("total_results")
    public long totalResults;

    @JsonProperty("total_pages")
    public long totalPages;

    @JsonProperty("results")
    public List<Result> results;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result {

        @JsonProperty("id")
        public int id;

        @JsonProperty("vote_count")
        public int voteCount;

        @JsonProperty("video")
        public boolean video;

        @JsonProperty("vote_average")
        public double voteAverage;

        @JsonProperty("title")
        public String title;

        @JsonProperty("popularity")
        public double popularity;

        @JsonProperty("poster_path")
        public String posterPath;

        @JsonProperty("original_language")
        public String originalLanguage;

        @JsonProperty("original_title")
        public String originalTitle;

        @JsonProperty("genre_ids")
        public List<Long> genreIds;

        @JsonProperty("backdrop_path")
        public String backdropPath;

        @JsonProperty("adult")
        public boolean adult;

        @JsonProperty("overview")
        public String overview;

        @JsonProperty("release_date")
        public String releaseDate;
    }
}