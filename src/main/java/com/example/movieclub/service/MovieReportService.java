package com.example.movieclub.service;

import com.example.movieclub.domain.Movie;
import com.example.movieclub.domain.MovieReport;
import com.example.movieclub.domain.User;
import com.example.movieclub.repository.MovieReportRepository;
import com.example.movieclub.repository.MovieRepository;
import com.example.movieclub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieReportService {
    private final MovieReportRepository movieErrorReportRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public MovieReport reportMovie(String userEmail, long movieId, String reportContent) {
        MovieReport movieErrorReport = new MovieReport();
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        Movie movie = movieRepository.findById(movieId).orElseThrow();
        movieErrorReport.setUser(user);
        movieErrorReport.setMovie(movie);
        movieErrorReport.setDescription(reportContent);
        movieErrorReportRepository.save(movieErrorReport);
        return movieErrorReport;
    }
}
