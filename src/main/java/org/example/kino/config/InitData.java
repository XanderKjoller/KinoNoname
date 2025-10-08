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
        movie1.setPoster("https://filmnoerden.dk/wp-content/uploads/2015/05/Inception-1200x1777.jpg");
        movieRepository.save(movie1);

        Movie movie2 = new Movie();
        movie2.setName("The Dark Knight");
        movie2.setDuration(152);
        movie2.setCategory("Action");
        movie2.setAgeRating("PG-13");
        movie2.setActors("Christian Bale, Heath Ledger");
        movie2.setReleaseDate(LocalDate.of(2008, 7, 18));
        movie2.setPoster("https://img-cdn.sfanytime.com/COVERM/COVERM_b9e21514-0507-4965-a0a4-7ebb3971dd90_01.jpg?w=415&ar=0.692&fit=crop&fm=pjpg&s=6fb117a3ae4fde327e732ce00e7602db");
        movieRepository.save(movie2);

        Movie movie3 = new Movie();
        movie3.setName("Interstellar");
        movie3.setDuration(169);
        movie3.setCategory("Sci-Fi");
        movie3.setAgeRating("PG-13");
        movie3.setActors("Matthew McConaughey, Anne Hathaway");
        movie3.setReleaseDate(LocalDate.of(2014, 11, 7));
        movie3.setPoster("https://static.posters.cz/image/1300/plakater/interstellar-one-sheet-i23157.jpg");
        movieRepository.save(movie3);

        Movie movie4 = new Movie();
        movie4.setName("Titanic");
        movie4.setDuration(195);
        movie4.setCategory("Romance");
        movie4.setAgeRating("PG-13");
        movie4.setActors("Leonardo DiCaprio, Kate Winslet");
        movie4.setReleaseDate(LocalDate.of(1997, 12, 19));
        movie4.setPoster("https://cdn11.bigcommerce.com/s-ydriczk/images/stencil/1500x1500/products/86477/86033/titanic_3d_2012_style_b_original_movie_poster_buy_now_at_starstills__21888__87786.1394515302.jpg?c=2&imbypass=on");
        movieRepository.save(movie4);

        Movie movie5 = new Movie();
        movie5.setName("Avengers: Endgame");
        movie5.setDuration(181);
        movie5.setCategory("Action");
        movie5.setAgeRating("PG-13");
        movie5.setActors("Robert Downey Jr., Chris Evans, Scarlett Johansson");
        movie5.setReleaseDate(LocalDate.of(2019, 4, 26));
        movie5.setPoster("https://m.media-amazon.com/images/I/71niXI3lxlL._AC_UF894,1000_QL80_.jpg");
        movieRepository.save(movie5);

        Movie movie6 = new Movie();
        movie6.setName("The Matrix");
        movie6.setDuration(136);
        movie6.setCategory("Sci-Fi");
        movie6.setAgeRating("R");
        movie6.setActors("Keanu Reeves, Carrie-Anne Moss, Laurence Fishburne");
        movie6.setReleaseDate(LocalDate.of(1999, 3, 31));
        movie6.setPoster("https://m.media-amazon.com/images/I/51vpnbwFHrL._AC_.jpg");
        movieRepository.save(movie6);

        Movie movie7 = new Movie();
        movie7.setName("Joker");
        movie7.setDuration(122);
        movie7.setCategory("Drama");
        movie7.setAgeRating("R");
        movie7.setActors("Joaquin Phoenix, Robert De Niro");
        movie7.setReleaseDate(LocalDate.of(2019, 10, 4));
        movie7.setPoster("https://cdn11.bigcommerce.com/s-ydriczk/images/stencil/1500x1500/products/89058/93685/Joker-2019-Final-Style-steps-Poster-buy-original-movie-posters-at-starstills__62518.1669120603.jpg?c=2&imbypass=on");
        movieRepository.save(movie7);

        Movie movie8 = new Movie();
        movie8.setName("Frozen II");
        movie8.setDuration(103);
        movie8.setCategory("Animation");
        movie8.setAgeRating("PG");
        movie8.setActors("Kristen Bell, Idina Menzel, Josh Gad");
        movie8.setReleaseDate(LocalDate.of(2019, 11, 22));
        movie8.setPoster("https://i.ebayimg.com/images/g/4PoAAOSwf2li4dk8/s-l1600.jpg");
        movieRepository.save(movie8);

        Movie movie9 = new Movie();
        movie9.setName("The Godfather");
        movie9.setDuration(175);
        movie9.setCategory("Crime");
        movie9.setAgeRating("R");
        movie9.setActors("Marlon Brando, Al Pacino, James Caan");
        movie9.setReleaseDate(LocalDate.of(1972, 3, 24));
        movie9.setPoster("https://storage.googleapis.com/pod_public/1300/262788.jpg");
        movieRepository.save(movie9);

        Movie movie10 = new Movie();
        movie10.setName("Spider-Man: No Way Home");
        movie10.setDuration(148);
        movie10.setCategory("Action");
        movie10.setAgeRating("PG-13");
        movie10.setActors("Tom Holland, Zendaya, Benedict Cumberbatch");
        movie10.setReleaseDate(LocalDate.of(2021, 12, 17));
        movie10.setPoster("https://cdn.marvel.com/content/1x/snh_online_6072x9000_posed_01.jpg");
        movieRepository.save(movie10);


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
