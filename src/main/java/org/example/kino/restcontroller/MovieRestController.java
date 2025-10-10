package org.example.kino.restcontroller;

import org.example.kino.model.Movie;
import org.example.kino.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class MovieRestController {
    @Autowired
    MovieRepository movieRepository;
    @PostMapping("/")
    public List<Movie> frontpage(){
        return movieRepository.findAll();
    }

    @GetMapping("/MovieData/{movieID}")
    private Movie getMovieData(@PathVariable("movieID") int movieID)
    {
        Movie response = movieRepository.findById(movieID).orElseThrow();
        System.out.println(response.getMovieID());
        return response;
    }
}
