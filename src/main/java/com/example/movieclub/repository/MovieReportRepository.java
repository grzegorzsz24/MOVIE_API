package com.example.movieclub.repository;

import com.example.movieclub.domain.MovieReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieReportRepository extends JpaRepository<MovieReport, Long> {
}
