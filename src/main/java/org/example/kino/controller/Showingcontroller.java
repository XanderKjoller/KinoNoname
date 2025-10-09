package org.example.kino.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class Showingcontroller {

    @GetMapping("showing/{movieID}")
    private String showingShower(@PathVariable("movieID") int movieID, Model model) {
        model.addAttribute("movieID ",movieID);
        return ("ShowingsForMovie");
    }
}
