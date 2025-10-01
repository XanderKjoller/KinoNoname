package org.example.kino.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
@Entity
public class Show {
    @Id
    private int showID;
    private int movieID;
    private int price;
    private int roomID;
    private LocalDateTime period;
}