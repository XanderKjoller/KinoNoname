package org.example.kino.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Snack")

public class Snack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int snackID;
    private String name;
    @Column(length = 1000)

    private String image;
    private int price;


    @OneToMany(mappedBy = "snack")
    @JsonIgnore
    private List<SnackReservation> snackReservations;

    public int getSnackID() {
        return snackID;
    }



    public List<SnackReservation> getSnackReservations() {
        return snackReservations;
    }

    public void setSnackReservations(List<SnackReservation> snackReservations) {
        this.snackReservations = snackReservations;
    }

    public void setSnackID(int snackID) {
        this.snackID = snackID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}