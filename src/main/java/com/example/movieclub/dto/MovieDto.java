package com.example.movieclub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MovieDto {
    private Long id;
    private String title;
    private String originalTitle;
    private Integer releaseYear;
    private String genre;
    private boolean promoted;
}
