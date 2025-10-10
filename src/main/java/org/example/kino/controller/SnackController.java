package org.example.kino.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.kino.model.Booking;
import org.example.kino.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        return "newSnack";
    }
    @GetMapping("/snackRedirect")
    public String snackRedirect(HttpServletRequest request, HttpSession session) {
        int bookingID = Integer.parseInt(request.getParameter("bookingID"));
        //System.out.println("bookingID: " + bookingID);
        //Booking booking = bookingRepository.findById(bookingID).get();
        session.setAttribute("bookingID", bookingID);
        return "redirect:/snacks";
    }


    @GetMapping("/snack/{id}")
    public String snack(@PathVariable int id) {
        return "putSnack";
    }


}
