package org.example.kino.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Booking {
    @Id
    private int bookingID;
    private int showID;
    private int seatID;
    private int userID;
    private int userGame;
}
