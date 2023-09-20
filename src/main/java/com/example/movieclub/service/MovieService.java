package com.example.movieclub.service;

import com.example.movieclub.domain.Genre;
import com.example.movieclub.domain.Movie;
import com.example.movieclub.dto.GenreDto;
import com.example.movieclub.dto.MovieDto;
import com.example.movieclub.dto.MovieGenresDto;
import com.example.movieclub.dto.MovieSaveDto;
import com.example.movieclub.mapper.GenreDtoMapper;
import com.example.movieclub.mapper.MovieDtoMapper;
import com.example.movieclub.repository.GenreRepository;
import com.example.movieclub.repository.MovieRepository;
import com.example.movieclub.storage.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final FileStorageService fileStorageService;
    private final MovieDtoMapper mapper;
    public List<MovieDto> findAllPromotedMovies() {
        return movieRepository.findAllByPromotedIsTrue().stream()
                .map(MovieDtoMapper::map)
                .toList();
    }

    public Optional<MovieDto> findMovieById(long id) {
        return movieRepository.findById(id).map(MovieDtoMapper::map);
    }
    public Optional<MovieGenresDto> findMovieDtoById(long id) {
        return movieRepository.findById(id).map(MovieDtoMapper::mapToDto);
    }

    public List<MovieDto> findMoviesByGenreName(String genre) {
        return movieRepository.findAllByGenre_NameIgnoreCase(genre).stream()
                .map(MovieDtoMapper::map)
                .toList();
    }

    public Movie addMovie(MovieSaveDto movieToSave) {
        Movie movie = new Movie();
        movie.setTitle(movieToSave.getTitle());
        movie.setOriginalTitle(movieToSave.getOriginalTitle());
        movie.setPromoted(movieToSave.isPromoted());
        movie.setReleaseYear(movieToSave.getReleaseYear());
        movie.setShortDescription(movieToSave.getShortDescription());
        movie.setDescription(movieToSave.getDescription());
        movie.setYoutubeTrailerId(movieToSave.getYoutubeTrailerId());
        Genre genre = genreRepository.findByNameIgnoreCase(movieToSave.getGenre()).orElseThrow();
        movie.setGenre(genre);
        if (movieToSave.getPoster() != null) {
            String savedFileName = fileStorageService.saveImage(movieToSave.getPoster());
            movie.setPoster(savedFileName);
        }
        return movieRepository.save(movie);
    }

    public List<MovieDto> findTopMovies(int size) {
        Pageable page = Pageable.ofSize(size);
        return movieRepository.findTopByRating(page).stream()
                .map(MovieDtoMapper::map)
                .toList();
    }

    public void updateMovie(MovieGenresDto movieToUpdate) {
        Movie movie = mapper.map(movieToUpdate);
        movieRepository.save(movie);
    }

    public void deleteMovie(long id) {
        movieRepository.deleteById(id);
    }

    public List<MovieDto> findAllWithFilters(String genre, Integer releaseYear, int page) {
        Pageable size = PageRequest.of(page, 10);
        return movieRepository.findAllByGenre_NameAndReleaseYear(genre, releaseYear, size).stream()
                .map(MovieDtoMapper::map)
                .toList();
    }
}
