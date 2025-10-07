package org.example.kino.restcontroller;

import jakarta.servlet.http.HttpSession;
import org.example.kino.model.*;
import org.example.kino.repositories.BookingRepository;
import org.example.kino.repositories.MovieRepository;
import org.example.kino.repositories.ShowRepository;
import org.example.kino.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
   /* @GetMapping("/movieData")
    public List<Show> movieData(@RequestBody Booking booking) {

        List<Show> shows = showRepository.findAll();
        for (int i = 0; i < shows.size(); i++) {
            if(shows.get(i).getShowID() != booking.getShow().getShowID()){
                System.out.println("removing" + shows.get(i).getShowID());
                shows.remove(shows.get(i));
                i--;
            }
        }
        return shows;
    }
    @GetMapping("/showData")
    public List<Booking> showData(@RequestBody Show show)  {

        List<Movie> movies = movieRepository.findAll();
        for (int i = 0; i < movies.size(); i++) {
            if(movies.get(i).getShowID() != booking.getShow().getShowID()){
                System.out.println("removing" + movies.get(i).getShowID());
                movies.remove(movies.get(i));
                i--;
            }
        }
        return movies;
    }*/

}
