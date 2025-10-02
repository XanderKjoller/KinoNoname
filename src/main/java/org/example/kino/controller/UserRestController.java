package org.example.kino.controller;

import org.example.kino.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String root(){

        return "ro";
    }
}
