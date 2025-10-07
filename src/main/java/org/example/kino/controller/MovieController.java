package org.example.kino.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class MovieController {
    @GetMapping("/")
    public String frontpage(){
        return "frontpage";
    }}
