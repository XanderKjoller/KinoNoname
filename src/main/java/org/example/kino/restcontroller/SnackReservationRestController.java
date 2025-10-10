package org.example.kino.restcontroller;

import org.example.kino.model.Booking;
import org.example.kino.model.Snack;
import org.example.kino.model.SnackReservation;
import org.example.kino.repositories.BookingRepository;
import org.example.kino.repositories.SnackRepository;
import org.example.kino.repositories.SnackReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SnackReservationRestController {
    @Autowired
    SnackRepository snackRepository;
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    SnackReservationRepository snackReservationRepository;


    @PostMapping("/addSnackReservation")
    public ResponseEntity<?> addSnack(@RequestBody SnackReservation newSnackReservation) {

        // Extract entities
        Snack snack = newSnackReservation.getSnack();
        Booking booking = newSnackReservation.getBooking();

        // Validate
        if (snack == null || booking == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Snack and Booking must not be null");
        }

        // Fetch managed entities from DB to avoid detached state
        Snack foundSnack = snackRepository.findById(snack.getSnackID()).orElse(null);
        Booking foundBooking = bookingRepository.findById(booking.getBookingID()).orElse(null);

        if (foundSnack == null || foundBooking == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Snack or Booking not found");
        }

        // Create and save a new SnackReservation
        SnackReservation snackReservationToSave = new SnackReservation();
        snackReservationToSave.setSnack(foundSnack);
        snackReservationToSave.setBooking(foundBooking);

        snackReservationRepository.save(snackReservationToSave);

        // Return HTTP 201 Created with saved entity
        return ResponseEntity.status(HttpStatus.CREATED).body(snackReservationToSave);
    }
}
