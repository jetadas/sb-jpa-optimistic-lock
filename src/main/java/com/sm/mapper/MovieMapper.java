package com.sm.mapper;

import com.sm.dto.MovieDto;
import com.sm.entity.Movie;

public class MovieMapper {
    public static MovieDto of(Movie movie) {
        return new MovieDto().setId(movie.getId()).setVersion(movie.getVersion()).setTitle(movie.getTitle()).setRating(movie.getRating());
    }

    public static Movie of(MovieDto movie) {
        return new Movie().setId(movie.getId()).setVersion(movie.getVersion()).setTitle(movie.getTitle()).setRating(movie.getRating());
    }
}
