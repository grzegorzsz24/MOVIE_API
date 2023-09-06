package com.example.movieclub.service;

import com.example.movieclub.domain.Movie;
import com.example.movieclub.domain.Rating;
import com.example.movieclub.domain.User;
import com.example.movieclub.repository.MovieRepository;
import com.example.movieclub.repository.RatingRepository;
import com.example.movieclub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public Rating addOrUpdateRating(String userEmail, long movieId, int rating) {
        Rating ratingToSaveOrUpdate = ratingRepository.findByUser_EmailAndMovie_Id(userEmail, movieId)
                .orElseGet(Rating::new);
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        Movie movie = movieRepository.findById(movieId).orElseThrow();
        ratingToSaveOrUpdate.setUser(user);
        ratingToSaveOrUpdate.setMovie(movie);
        ratingToSaveOrUpdate.setRating(rating);
        ratingRepository.save(ratingToSaveOrUpdate);
        return ratingToSaveOrUpdate;
    }

    public Optional<Integer> getUserRatingForMovie(String userEmail, long movieId) {
        return ratingRepository.findByUser_EmailAndMovie_Id(userEmail, movieId)
                .map(Rating::getRating);
    }
}
