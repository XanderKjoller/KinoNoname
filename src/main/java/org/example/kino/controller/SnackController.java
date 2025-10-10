package org.example.kino.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.kino.model.Booking;
import org.example.kino.model.SeatReservation;
import org.example.kino.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.example.kino.controller.UserController.getSession;

@Controller
public class SnackController {

    @Autowired
    BookingRepository bookingRepository;

    @GetMapping("snacks")
    public String snacks(HttpSession session, Model model) {
        getSession(model, session);
        return "snacks";
    }

    @GetMapping("snack")
    public String snack(HttpSession session, Model model) {
        getSession(model, session);
        return "snackUC";
    }
    @GetMapping("/snackRedirect")
    public String snackRedirect(HttpServletRequest request, HttpSession session) {
        int bookingID = Integer.parseInt(request.getParameter("bookingID"));
        session.setAttribute("booking", bookingRepository.findById(bookingID));
        return "redirect:snacks";
    }





}
