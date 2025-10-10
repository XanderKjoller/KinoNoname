package org.example.kino.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "Booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingID;

    private String ticketCode;

    @PrePersist
    public void prePersist() {
        // Temporary placeholder to ensure it's not null before persist
        ticketCode = "PENDING";
    }

    @PostPersist
    public void postPersist() {
        // Once bookingID is generated, create the final ticket code
        String randomPart = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        ticketCode = "TK-" + randomPart + "-" + bookingID;
    }

    @ManyToOne
    //@JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "showID")
    private Show show;
    private int seatRow;
    private int seatColumn;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
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


    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatColumn() {
        return seatColumn;
    }

    public void setSeatColumn(int seatColumn) {
        this.seatColumn = seatColumn;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }
}
