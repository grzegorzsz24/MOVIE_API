package com.example.movieclub.mapper;

import com.example.movieclub.domain.Movie;
import com.example.movieclub.dto.MovieDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class MovieDtoMapper {
    public static MovieDto map(Movie movie) {
        MovieDto movieDto = new MovieDto();
        BeanUtils.copyProperties(movie, movieDto);
        movieDto.setGenre(movie.getGenre().getName());
        return movieDto;
    }
}
