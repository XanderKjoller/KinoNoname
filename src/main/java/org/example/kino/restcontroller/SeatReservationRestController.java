package org.example.kino.restcontroller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.websocket.Session;
import org.example.kino.model.Booking;
import org.example.kino.model.SeatReservation;
import org.example.kino.model.Show;
import org.example.kino.model.User;
import org.example.kino.repositories.BookingRepository;
import org.example.kino.repositories.SeatReservationRepository;
import org.example.kino.repositories.ShowRepository;
import org.example.kino.repositories.UserRepository;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/SeatReservationData")
    public List<SeatReservation> seatReservation(HttpServletRequest request, HttpSession session)  {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        //ændre når man kan få det fra sidste side
        int showID = Integer.parseInt(request.getParameter("showID"));

        List<SeatReservation> reservedSeats = seatReservationRepository.findAll();
        for (int i = 0; i < reservedSeats.size(); i++) {
            if(reservedSeats.get(i).getTimeLog() != null && reservedSeats.get(i).getTimeLog().isBefore(LocalDateTime.now().minus(1, ChronoUnit.MINUTES))
            ||  (loggedInUser!= null && reservedSeats.get(i).getUser() != null && reservedSeats.get(i).getUser().getUserID() == loggedInUser.getUserID())){
                seatReservationRepository.delete(reservedSeats.get(i));
                reservedSeats.remove(reservedSeats.get(i));
                i--;
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
    public ResponseEntity<Object> ReserveSeat(@RequestBody SeatReservation seatReservation, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        seatReservation.setTimeLog(LocalDateTime.now());
        seatReservation.setShow(showRepository.findById(seatReservation.getShow().getShowID()).get());
        if(loggedInUser != null)
            seatReservation.setUser(userRepository.getReferenceById(loggedInUser.getUserID()));
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

    @GetMapping("/GetShow")
    public ResponseEntity<Show> getShow(HttpSession session) {
        if (session.getAttribute("showID") == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        int showID = (int) session.getAttribute("showID");
        return ResponseEntity.ok(showRepository.findById(showID).get());
    }

    @PostMapping("/BookTicket")
    @Transactional
    public ResponseEntity<Booking> bookTicket(@RequestBody Booking booking, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if(loggedInUser != null)
            booking.setUser(userRepository.getReferenceById(loggedInUser.getUserID()));


        booking.setShow(showRepository.findById(booking.getShow().getShowID()).get());
        System.out.println(booking.getShow().getMovie().getPoster());
        for (Booking b : bookingRepository.findAll()) {
            if(b.getShow().getShowID() == booking.getShow().getShowID()
                    && b.getSeatColumn() == booking.getSeatColumn()
                    && b.getSeatRow() == booking.getSeatRow()) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        Booking savedBooking = bookingRepository.save(booking);
        if (savedBooking == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
        }

    }
}
