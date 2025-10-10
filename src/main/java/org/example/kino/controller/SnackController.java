package org.example.kino.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SnackController {
    @GetMapping("snacks")
    public String snacks(HttpSession session, Model model) {
        getSession(model, session);
        return "snacks";
    }

    @GetMapping("snack")
    public String snack(){
        return "newSnack";
    }
    @GetMapping("/snackRedirect")
    public String snackRedirect(HttpServletRequest request, HttpSession session) {
        int bookingID = Integer.parseInt(request.getParameter("bookingID"));
        session.setAttribute("booking", bookingRepository.findById(bookingID));
        return "redirect:snacks";
    }


    @GetMapping("/snack/{id}")
    public String snack(@PathVariable int id) {

        return "putSnack";
    }


}
