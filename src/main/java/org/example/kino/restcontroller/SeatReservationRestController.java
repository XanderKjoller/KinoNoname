package org.example.kino.restcontroller;

import org.example.kino.model.SeatReservation;
import org.example.kino.repositories.SeatReservationRepository;
import org.example.kino.repositories.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

            if(reservedSeats.get(i).getShow().getShowID() != showID){
                System.out.println("removing" + reservedSeats.get(i).getShow().getShowID());
                reservedSeats.remove(reservedSeats.get(i));
                i--;
            }
        }
        return reservedSeats;
    }
    @PostMapping("/ReserveSeat")
    public ResponseEntity<Object> ReserveSeat(@RequestBody SeatReservation seatReservation) {
        SeatReservation savedSeatReservation = seatReservationRepository.save(seatReservation);
        if (savedSeatReservation == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(savedSeatReservation, HttpStatus.CREATED);
        }
    }
    /*@DeleteMapping("/DeleteSeat")
    public ResponseEntity<Void> DeleteSeat(@RequestBody SeatReservation seatReservation) {
        List<SeatReservation> reservedSeats = seatReservationRepository.findAll();
       // for (int i = 0; i < reservedSeats.size(); i++) {
            //if()
        //}
       /* SeatReservation savedSeatReservation = seatReservationRepository.getReferenceById(id);
        if (savedSeatReservation == null) {
            return ResponseEntity.notFound().build();
        } else {
            seatReservationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }*/
    //}
}
