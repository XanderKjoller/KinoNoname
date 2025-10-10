package org.example.kino.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SnackController {
    @GetMapping("snacks")
    public String snacks(){
        return "snacks";
    }

    @GetMapping("snack")
    public String snack(){
        return "snackUC";
    }






}
