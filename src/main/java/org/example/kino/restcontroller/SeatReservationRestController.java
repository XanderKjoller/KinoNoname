package org.example.kino.restcontroller;

import org.example.kino.model.SeatReservation;
import org.example.kino.repositories.SeatReservationRepository;
import org.example.kino.repositories.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;

@RestController
@CrossOrigin("*")
public class SeatReservationRestController {
    @Autowired
    ShowRepository showRepository;

    @Autowired
    SeatReservationRepository seatReservationRepository;

    @GetMapping("/SeatReservationData")
    public List<SeatReservation> seatReservation()  {


        //ændre når man kan få det fra sidste side
        int showID = 1;

        List<SeatReservation> reservedSeats = seatReservationRepository.findAll();
        for (int i = 0; i < reservedSeats.size(); i++) {
            if(reservedSeats.get(i).getTimeLog() != null && reservedSeats.get(i).getTimeLog().isBefore(LocalDateTime.now().minus(1, ChronoUnit.MINUTES))){
                seatReservationRepository.delete(reservedSeats.get(i));
            }
            else if(reservedSeats.get(i).getShow().getShowID() != showID){
                System.out.println("removing" + reservedSeats.get(i).getShow().getShowID());
                reservedSeats.remove(reservedSeats.get(i));
                i--;
            }
        }
        return reservedSeats;
    }
    @PostMapping("/ReserveSeat")
    public ResponseEntity<Object> ReserveSeat(@RequestBody SeatReservation seatReservation) {
        seatReservation.setTimeLog(LocalDateTime.now());
        for (SeatReservation s : seatReservationRepository.findAll()) {
            if(s.getShow().getShowID() == seatReservation.getShow().getShowID()
                    && s.getSeatColumn() == seatReservation.getSeatColumn()
                    && s.getSeatRow() == seatReservation.getSeatRow()) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        SeatReservation savedSeatReservation = seatReservationRepository.save(seatReservation);
        if (savedSeatReservation == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(savedSeatReservation, HttpStatus.CREATED);
        }
    }
    @DeleteMapping("/DeleteSeat")
    public ResponseEntity<Object> DeleteSeat(@RequestBody SeatReservation seatReservation) {
        List<SeatReservation> reservedSeats = seatReservationRepository.findAll();
        SeatReservation seat = null;
        for (int i = 0; i < reservedSeats.size(); i++) {
            if(reservedSeats.get(i).getShow().getShowID() == seatReservation.getShow().getShowID()
            && reservedSeats.get(i).getSeatColumn() == seatReservation.getSeatColumn()
            && reservedSeats.get(i).getSeatRow() == seatReservation.getSeatRow()) {
                seat = reservedSeats.get(i);
            }
        }
        if (seat == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            seatReservationRepository.delete(seat);
            return new ResponseEntity<>(seat, HttpStatus.CREATED);
        }
    }
}
