package org.example.kino.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.example.kino.controller.UserController.getSession;

@Controller

public class MovieController {
    @GetMapping("/")
    public String frontpage(HttpSession session, Model model) {
        getSession(model, session);
        return "frontpage";
    }}
