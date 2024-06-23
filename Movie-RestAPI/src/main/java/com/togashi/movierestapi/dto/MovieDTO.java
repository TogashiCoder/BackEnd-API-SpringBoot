package com.togashi.movierestapi.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MovieDTO {
    private int id;
    private String title;
    private LocalDate releaseDate;
    private String genre;
    private String director;
    private Double rating;
}
