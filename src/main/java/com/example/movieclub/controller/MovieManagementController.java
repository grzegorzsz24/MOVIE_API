package com.example.movieclub.controller;

import com.example.movieclub.domain.Movie;
import com.example.movieclub.dto.MovieDto;
import com.example.movieclub.dto.MovieGenresDto;
import com.example.movieclub.dto.MovieSaveDto;
import com.example.movieclub.service.GenreService;
import com.example.movieclub.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class MovieManagementController {
    private final MovieService movieService;
    private final ObjectMapper objectMapper;

    @PostMapping("/add-movie")
    public ResponseEntity<Movie> addMovie(@ModelAttribute MovieSaveDto movie) {
        Movie savedMovie = movieService.addMovie(movie);
        URI savedMovieUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMovie.getId())
                .toUri();
        return ResponseEntity.created(savedMovieUri).body(savedMovie);
    }

    @PatchMapping("/update-movie/{id}")
    ResponseEntity<?> updateMovie(@PathVariable Long id, @RequestBody JsonMergePatch patch) {
        try {
            MovieGenresDto movieDto = movieService.findMovieDtoById(id).orElseThrow();
            MovieGenresDto moviePatched = applyPatch(movieDto, patch);
            movieService.updateMovie(moviePatched);

        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    private MovieGenresDto applyPatch(MovieGenresDto movieDto, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode movieNode = objectMapper.valueToTree(movieDto);
        JsonNode moviePatchedNode = patch.apply(movieNode);
        return objectMapper.treeToValue(moviePatchedNode, MovieGenresDto.class);
    }

    @DeleteMapping("/delete-movie/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

}
