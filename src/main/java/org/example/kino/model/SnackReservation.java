package org.example.kino.model;

import jakarta.persistence.*;

@Entity
@Table(name = "SnackReservation")
public class SnackReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int snackReservationID;

    @ManyToOne
    @JoinColumn(name = "bookingID")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "snackID")
    private Snack snack;

    public int getSnackReservationID() {
        return snackReservationID;
    }

    public void setSnackReservationID(int snackReservationID) {
        this.snackReservationID = snackReservationID;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Snack getSnack() {
        return snack;
    }

    public void setSnack(Snack snack) {
        this.snack = snack;
    }

}
