package org.example.kino.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.kino.model.SeatReservation;
import org.example.kino.repositories.SeatReservationRepository;
import org.example.kino.repositories.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SeatReservationController {

    @Autowired
    ShowRepository showRepository;

    @GetMapping("/SeatReservation")
    public String seatReservation(HttpServletRequest request, HttpSession session, Model model)  {
        int showID = Integer.parseInt(request.getParameter("showID"));
        session.setAttribute("showID", showID);
        model.addAttribute("show", showRepository.findById(showID).get());
        return "seatReservation";
    }

    @GetMapping("/Bookings")
    public String bookings()  {
        return "bookings";
    }
}