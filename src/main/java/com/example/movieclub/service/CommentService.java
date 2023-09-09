package com.example.movieclub.service;

import com.example.movieclub.domain.Comment;
import com.example.movieclub.domain.Movie;
import com.example.movieclub.domain.User;
import com.example.movieclub.repository.CommentRepository;
import com.example.movieclub.repository.MovieRepository;
import com.example.movieclub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public Comment addOrUpdateComment(String userEmail, long movieId, String commentContent) {
        Comment commentToSaveOrUpdate = commentRepository.findByUser_EmailAndMovie_Id(userEmail, movieId)
                .orElseGet(Comment::new);
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        Movie movie = movieRepository.findById(movieId).orElseThrow();
        commentToSaveOrUpdate.setUser(user);
        commentToSaveOrUpdate.setMovie(movie);
        commentToSaveOrUpdate.setContent(commentContent);
        commentRepository.save(commentToSaveOrUpdate);
        return commentToSaveOrUpdate;
    }

    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }
}
