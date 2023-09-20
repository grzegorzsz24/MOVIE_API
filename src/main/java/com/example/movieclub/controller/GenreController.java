package com.example.movieclub.controller;

import com.example.movieclub.dto.GenreDto;
import com.example.movieclub.dto.MovieDto;
import com.example.movieclub.service.GenreService;
import com.example.movieclub.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;
    private final MovieService movieService;

    @GetMapping("/{name}")
    public ResponseEntity<List<MovieDto>> getGenreMovies(@PathVariable String name) {
        GenreDto genre = genreService.findGenreByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(movieService.findMoviesByGenreName(name));
    }

    @GetMapping()
    public ResponseEntity<List<GenreDto>> getGenreList() {
        return ResponseEntity.ok(genreService.findAllGenres());
    }
}
