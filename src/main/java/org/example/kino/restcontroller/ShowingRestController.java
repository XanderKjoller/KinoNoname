package org.example.kino.restcontroller;

import org.example.kino.model.Show;
import org.example.kino.repositories.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController

public class ShowingRestController {

    @Autowired
    ShowRepository showRepository;

    @GetMapping("/Showings/{movieId}")
    private List<Show> showings(@PathVariable("movieId")int movieId)
    {
        List<Show> showings = showRepository.findAll();
        List<Show> filteredShowings = new ArrayList<>();
        for (Show i : showings) {
            if(i.getMovie().getMovieID() == movieId){
                filteredShowings.add(i);
            }
        }
        return filteredShowings;
    }
}
