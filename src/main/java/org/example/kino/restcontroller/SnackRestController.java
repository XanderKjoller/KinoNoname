package org.example.kino.restcontroller;

import org.example.kino.model.Snack;
import org.example.kino.repositories.SnackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SnackRestController {
    @Autowired
    SnackRepository snackRepository;
    @PostMapping("/snacks")
    public List<Snack> snacks (){
        return snackRepository.findAll();
    }
}
