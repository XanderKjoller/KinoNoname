package org.example.kino.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class ShowingRestController {

    @GetMapping("/Showings")
    private String showings(@RequestParam("movieId")int movieId)
    {
        return "Showings";
    }
}
