package com.example.movieclub.repository;

import com.example.movieclub.domain.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAllByPromotedIsTrue();
    List<Movie> findAllByGenre_NameIgnoreCase(String genre);
    @Query("select m from Movie m join m.ratings r group by m order by avg(r.rating) desc")
    List<Movie> findTopByRating(Pageable page);
}
