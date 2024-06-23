package com.togashi.movierestapi.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SerieDTO {
    private int id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String genre;
    private String creator;
    private Double rating;
}
