package org.example.kino.model;

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