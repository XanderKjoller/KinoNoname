package org.example.kino.model;

import com.fasterxml.jackson.annotation.*;
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
    @JsonIdentityReference(alwaysAsId = true)
    private Show show;

    public int getSeatReservationID() {
        return seatReservationID;
    }

    public void setSeatReservationID(int seatReservationID) {
        this.seatReservationID = seatReservationID;
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

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }
}
