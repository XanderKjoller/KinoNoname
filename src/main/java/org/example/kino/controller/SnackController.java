package org.example.kino.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SnackController {
    @GetMapping("snacks")
    public String snacks(){
        return "snacks";
    }
}
