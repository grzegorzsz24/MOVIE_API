package com.example.movieclub.mapper;

import com.example.movieclub.domain.Genre;
import com.example.movieclub.domain.Movie;
import com.example.movieclub.domain.Rating;
import com.example.movieclub.dto.MovieDto;
import com.example.movieclub.dto.MovieGenresDto;
import com.example.movieclub.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieDtoMapper {
    private final GenreRepository genreRepository;

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

    public Movie map(MovieGenresDto movieDto) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(movieDto, movie);
        Genre genre = genreRepository.findByNameIgnoreCase(movieDto.getGenre()).get();
        movie.setGenre(genre);
        //movie.setRatings(movieDto.getRatings());
        return movie;
    }

    public static MovieGenresDto mapToDto(Movie movie) {
        MovieGenresDto movieGenresDto = new MovieGenresDto();
        BeanUtils.copyProperties(movie, movieGenresDto);
        movieGenresDto.setGenre(movie.getGenre().getName());
        return movieGenresDto;
    }
}
