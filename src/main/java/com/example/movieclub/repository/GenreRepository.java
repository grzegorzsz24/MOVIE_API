package com.example.movieclub.repository;

import com.example.movieclub.domain.Genre;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findByNameIgnoreCase(String name);
}
