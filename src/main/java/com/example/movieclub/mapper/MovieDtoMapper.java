package com.example.movieclub.mapper;

import com.example.movieclub.domain.Movie;
import com.example.movieclub.domain.Rating;
import com.example.movieclub.dto.MovieDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class MovieDtoMapper {
    public static MovieDto map(Movie movie) {
        double avgRating = movie.getRatings().stream()
                .map(Rating::getRating)
                .mapToDouble(val -> val)
                .average().orElse(0);
        int ratingCount = movie.getRatings().size();
        MovieDto movieDto = new MovieDto();
        BeanUtils.copyProperties(movie, movieDto);
        movieDto.setGenre(movie.getGenre().getName());
        movieDto.setAvgRating(avgRating);
        movieDto.setRatingCount(ratingCount);
        return movieDto;
    }
}
