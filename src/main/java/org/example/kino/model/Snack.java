package org.example.kino.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Snack {
    @Id
    private int snackID;
    private String name;
    private String image;
    private int price;
}