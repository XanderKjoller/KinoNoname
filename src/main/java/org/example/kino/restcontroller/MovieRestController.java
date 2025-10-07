package org.example.kino.restcontroller;

import org.example.kino.model.Movie;
import org.example.kino.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class MovieRestController {
    @Autowired
    MovieRepository movieRepository;
    @PostMapping("/")
    public List<Movie> frontpage(){
        return movieRepository.findAll();
    }
}
