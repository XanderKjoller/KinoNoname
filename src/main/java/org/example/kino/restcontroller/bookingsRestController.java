package org.example.kino.restcontroller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.kino.model.*;
import org.example.kino.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@CrossOrigin("*")
public class bookingsRestController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    ShowRepository showRepository;
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    SnackReservationRepository snackReservationRepository;

    @GetMapping("/bookingData")
    public List<Booking> bookingData(HttpSession session)  {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        List<Booking> bookings = bookingRepository.findAll();
        for (int i = 0; i < bookings.size(); i++) {
            if(bookings.get(i).getUser().getUserID() != loggedInUser.getUserID()){
                System.out.println("removing" + bookings.get(i).getShow().getShowID());
                bookings.remove(bookings.get(i));
                i--;
            }
        }
        return bookings;
    }
    /*@GetMapping("/allBookingDataFromShow")
    public List<Booking> allBookingDataFromShow(){

        List<Booking> bookings = bookingRepository.findAll();
        for (int i = 0; i < bookings.size(); i++) {
            if(bookings.get(i).getUser().getUserID() != loggedInUser.getUserID()){
                System.out.println("removing" + bookings.get(i).getShow().getShowID());
                bookings.remove(bookings.get(i));
                i--;
            }
        }
        return bookings;
    }*/
    @DeleteMapping("/DeleteBooking")
    public ResponseEntity<Object> deleteBooking(@RequestBody Booking booking) {
        if(booking == null)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        Booking bookingToDelete = bookingRepository.findById(booking.getBookingID()).get();
        bookingRepository.delete(bookingToDelete);
        return new ResponseEntity<>(bookingToDelete, HttpStatus.CREATED);
    }
    @GetMapping("/snackReservationData")
    public List<SnackReservation> snackReservationData(HttpServletRequest request) {
        int bookingID = Integer.parseInt(request.getParameter("bookingID"));
        List<SnackReservation> snackReservations = snackReservationRepository.findAll();
        for (int i = 0; i < snackReservations.size(); i++) {
            if(snackReservations.get(i).getBooking().getBookingID() != bookingID){
                snackReservations.remove(snackReservations.get(i));
                i--;
            }
        }
        return snackReservations;
    }
    @DeleteMapping("/DeleteSnack")
    public ResponseEntity<SnackReservation> deleteSnack(@RequestBody SnackReservation snackReservation) {
        snackReservationRepository.delete(snackReservation);
        return ResponseEntity.ok(snackReservation);
    }


}
