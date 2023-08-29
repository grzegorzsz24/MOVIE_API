package com.example.movieclub.controller;

import com.example.movieclub.domain.Genre;
import com.example.movieclub.dto.GenreDto;
import com.example.movieclub.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class GenreManagementController {
    private final GenreService genreService;

    @PostMapping("/add-genre")
    public ResponseEntity<Genre> addGenre(@RequestBody GenreDto genreDto) {
        Genre savedGenre = genreService.addGenre(genreDto);
        URI savedGenreUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedGenre.getId())
                .toUri();
        return ResponseEntity.created(savedGenreUri).body(savedGenre);
    }
}
