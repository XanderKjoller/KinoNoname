package org.example.kino.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Showtime") // Show is a reserved word in sql syntax thats why we use showtime instead
//this
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int showID;
    @ManyToOne
    @JoinColumn(name = "movieID")
    private Movie movie;

    private int price;

    @ManyToOne
    @JoinColumn(name = "roomID")
    private Room room;

    private LocalDateTime period;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Booking> bookings;

    @OneToMany(mappedBy = "show")
    @JsonIgnore
    private List<SeatReservation> seatReservations;


    public int getShowID() {
        return showID;
    }

    public void setShowID(int showID) {
        this.showID = showID;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDateTime getPeriod() {
        return period;
    }

    public void setPeriod(LocalDateTime period) {
        this.period = period;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public List<SeatReservation> getSeatReservations() {
        return seatReservations;
    }

    public void setSeatReservations(List<SeatReservation> seatReservations) {
        this.seatReservations = seatReservations;
    }
}