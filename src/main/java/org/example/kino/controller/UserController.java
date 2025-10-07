package org.example.kino.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class UserController {
    @GetMapping("/login")
    public String login(){
        return "loginPage";
    }


    @GetMapping("/signUp")
    public String signUp(){
        return "signUpPage";
    }


    @GetMapping("/")
    public String frontpage(){

        return "frontpage";
    }



}
