package org.example.kino.controller;

import jakarta.servlet.http.HttpSession;
import org.example.kino.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class UserController {
    @GetMapping("/login")
    public String login(HttpSession session, Model model) {
        getSession(model, session);
        return "loginPage";
    }


    @GetMapping("/signUp")
    public String signUp(HttpSession session, Model model) {
        getSession(model, session);
        return "signUpPage";
    }

    @GetMapping("/signOut")
    public String signOut(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    static public User getSession(Model model, HttpSession session){
        if(session != null)
        {
           User user = (User)session.getAttribute("loggedInUser");
           model.addAttribute("user",user);
           return user;
        }
        return null;
    }




}
