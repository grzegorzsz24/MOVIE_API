package com.example.movieclub.controller;

import com.example.movieclub.dto.MovieDto;
import com.example.movieclub.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/promoted")
    public ResponseEntity<List<MovieDto>> getPromotedMovies() {
        return ResponseEntity.ok(movieService.findAllPromotedMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable long id) {
        return movieService.findMovieById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/top10")
    public ResponseEntity<List<MovieDto>> findTop10() {
        return ResponseEntity.ok(movieService.findTopMovies(10));
    }
}
