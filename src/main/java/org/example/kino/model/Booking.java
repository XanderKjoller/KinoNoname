package org.example.kino.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingID;
    @ManyToOne
    @JoinColumn(name = "showID")
    private Show show;
    private int seatID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @OneToOne(mappedBy = "booking")
    private SnackReservation snackReservation;

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SnackReservation getSnackReservation() {
        return snackReservation;
    }

    public void setSnackReservation(SnackReservation snackReservation) {
        this.snackReservation = snackReservation;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }


    public int getSeatID() {
        return seatID;
    }

    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }


}
