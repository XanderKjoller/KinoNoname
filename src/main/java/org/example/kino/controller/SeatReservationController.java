package org.example.kino.controller;

import org.example.kino.model.SeatReservation;
import org.example.kino.repositories.SeatReservationRepository;
import org.example.kino.repositories.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SeatReservationController {
    @Autowired
    ShowRepository showRepository;

    @Autowired
    SeatReservationRepository seatReservationRepository;

    @GetMapping("/SeatReservation")
    public String seatReservation()  {
        return "seatReservation";
    }
}