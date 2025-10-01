package org.example.kino.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SnackReservation {
    @Id
    private int snackReservationID;
    private int bookingID;
    private int snackID;
}
