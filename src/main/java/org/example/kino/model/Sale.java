package org.example.kino.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
@Entity
public class Sale {
    @Id
    private int saleID;
    private int snackID;
    private LocalDateTime date;
    private int userID;
}