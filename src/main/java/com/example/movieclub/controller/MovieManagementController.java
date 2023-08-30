package com.example.movieclub.controller;

import com.example.movieclub.domain.Genre;
import com.example.movieclub.domain.Movie;
import com.example.movieclub.dto.MovieSaveDto;
import com.example.movieclub.service.GenreService;
import com.example.movieclub.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class MovieManagementController {
    private final MovieService movieService;
    private final GenreService genreService;

    @PostMapping("/add-movie")
    public ResponseEntity<Movie> addMovie(@ModelAttribute MovieSaveDto movie) {
        Movie savedMovie = movieService.addMovie(movie);
        URI savedMovieUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMovie.getId())
                .toUri();
        return ResponseEntity.created(savedMovieUri).body(savedMovie);
    }
}
