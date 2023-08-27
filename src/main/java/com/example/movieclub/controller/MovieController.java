package com.example.movieclub.controller;

import com.example.movieclub.dto.MovieDto;
import com.example.movieclub.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
