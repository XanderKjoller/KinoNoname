package org.example.kino.config;

import org.example.kino.model.*;

import org.example.kino.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private SnackRepository snackRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SeatReservationRepository seatReservationRepository;


    @Override
    public void run(String... args) throws Exception {
        // --- Create Rooms ---
        Room room1 = new Room();
        room1.setColumns(12);
        room1.setRows(20);
        roomRepository.save(room1);

        Room room2 = new Room();
        room2.setColumns(16);
        room2.setRows(25);
        roomRepository.save(room2);

        // --- Create Movies ---
        Movie movie1 = new Movie();
        movie1.setName("Inception");
        movie1.setDuration(148);
        movie1.setCategory("Sci-Fi");
        movie1.setAgeRating("PG-13");
        movie1.setActors("Leonardo DiCaprio");
        movie1.setReleaseDate(LocalDate.of(2010, 7, 16));
        movie1.setPoster("inception.jpg");
        movieRepository.save(movie1);

        Movie movie2 = new Movie();
        movie2.setName("The Dark Knight");
        movie2.setDuration(152);
        movie2.setCategory("Action");
        movie2.setAgeRating("PG-13");
        movie2.setActors("Christian Bale, Heath Ledger");
        movie2.setReleaseDate(LocalDate.of(2008, 7, 18));
        movie2.setPoster("darkknight.jpg");
        movieRepository.save(movie2);

        // --- Create Snacks ---
        Snack snack1 = new Snack();
        snack1.setName("Popcorn");
        snack1.setPrice(50);
        snack1.setImage("popcorn.jpg");
        snackRepository.save(snack1);

        Snack snack2 = new Snack();
        snack2.setName("Soda");
        snack2.setPrice(30);
        snack2.setImage("soda.jpg");
        snackRepository.save(snack2);

        // --- Create Users ---
        User u1 = new User();
        u1.setUsername("alice");
        u1.setPassword("customer");
        u1.setMail("alice@gmail.com");
        u1.setAuthority("CUSTOMER");
        userRepository.save(u1);

        User u2 = new User();
        u2.setUsername("bob");
        u2.setPassword("employee");
        u2.setMail("bob@gmail.com");
        u2.setAuthority("EMPLOYEE");
        userRepository.save(u2);


        // --- Create Showtimes ---
        Show st1 = new Show();
        st1.setMovie(movie1);
        st1.setRoom(room1);
        st1.setPrice(100);
        st1.setPeriod(LocalDateTime.of(2025, 10, 10, 20, 0));
        showRepository.save(st1);

        Show st2 = new Show();
        st2.setMovie(movie2);
        st2.setRoom(room2);
        st2.setPrice(120);
        st2.setPeriod(LocalDateTime.of(2025, 10, 11, 18, 0));
        showRepository.save(st2);

        // --- Create Bookings ---
        Booking b1 = new Booking();
        b1.setUser(u1);
        b1.setShow(st1);
        b1.setSeatRow(2);
        b1.setSeatColumn(2);
        bookingRepository.save(b1);

        Booking b2 = new Booking();
        b2.setUser(u1);
        b2.setShow(st2);
        b2.setSeatRow(1);
        b2.setSeatColumn(1);
        bookingRepository.save(b2);

        // --- Create SeatReservation ---
        SeatReservation sr1 = new SeatReservation();
        sr1.setShow(st1);
        sr1.setSeatRow(1);
        sr1.setSeatColumn(3);
        seatReservationRepository.save(sr1);

        SeatReservation sr2 = new SeatReservation();
        sr2.setShow(st1);
        sr2.setSeatRow(2);
        sr2.setSeatColumn(8);
        seatReservationRepository.save(sr2);

        System.out.println("✅ Initial Kino data loaded into database!");
    }
}
