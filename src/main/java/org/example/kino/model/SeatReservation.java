package org.example.kino.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "SeatReservation")
public class SeatReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int seatReservationID;

    private int seatRow;
    private int seatColumn;

    @ManyToOne
    @JoinColumn(name = "showID")
    private Show show;
}
