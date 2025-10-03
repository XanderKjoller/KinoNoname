package org.example.kino.controller;

import org.example.kino.repositories.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeatReservationRestController {
    @Autowired
    ShowRepository showRepository;
//hello
    @GetMapping("/SeatReservation")
    public String seatReservation() {

        return "SeatReservation";
    }
}
