package com.example.movieclub.mapper;

import com.example.movieclub.domain.Genre;
import com.example.movieclub.domain.Movie;
import com.example.movieclub.dto.GenreDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class GenreDtoMapper {
    public static GenreDto map(Genre genre) {
        GenreDto genreDto = new GenreDto();
        BeanUtils.copyProperties(genre, genreDto);
        return genreDto;
    }

    public Genre mapToGenre(GenreDto genreDto) {
        Genre genre = new Genre();
        BeanUtils.copyProperties(genreDto, genre);
        return genre;
    }
}
