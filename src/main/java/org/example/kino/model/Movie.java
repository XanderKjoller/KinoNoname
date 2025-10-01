package org.example.kino.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
@Entity
public class Movie {
    @Id
    private int movieID;
    private int duration;
    private String poster;
    private String category;
    private String ageRating;
    private String actors;
    private LocalDate releaseDate;
    private String name;
}