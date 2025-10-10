package org.example.kino.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.kino.model.Show;
import org.example.kino.model.User;
import org.example.kino.repositories.MovieRepository;
import org.example.kino.repositories.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.example.kino.controller.UserController.getSession;

@Controller
public class Showingcontroller {

    @Autowired
    ShowRepository showRepository;
    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("showing/{movieID}")
    private String showingShower(@PathVariable("movieID") int movieID, Model model, HttpSession session) {
        getSession(model, session);
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null && user.getAuthority().equals("EMPLOYEE")) {
            model.addAttribute("authority", true);
        }
        return ("ShowingsForMovie");
    }

    @GetMapping("showCreator/{movieID}")
    private String showCreator(Model model, HttpSession session, @PathVariable("movieID") int movieId) {
        getSession(model, session);
        User user = (User) session.getAttribute("loggedInUser");
        if (session.getAttribute("loggedInUser") != null && user.getAuthority().equals("EMPLOYEE")) {
            model.addAttribute("movieId", movieId);
            Show show = new Show();
            show.setMovie(movieRepository.getReferenceById(movieId));
            model.addAttribute("show", show);
            return "createShow";
        }
        return "redirect:/showing/" + movieId;
    }

    @PostMapping("createShowForMovie/{movieID}")
    private String createShow(Model model, HttpSession session, @PathVariable("movieID") int movieId, @ModelAttribute Show newShow) {
        getSession(model, session);
        User user = (User) session.getAttribute("loggedInUser");
        if (session.getAttribute("loggedInUser") != null && user.getAuthority().equals("EMPLOYEE")) {
            model.addAttribute("movieId", movieId);
            if (newShow.getMovie() != null && newShow.getRoom() != null) {
                showRepository.save(newShow);
            }
        }
        return "redirect:/showing/" + movieId;
    }

    @PostMapping("/deleteShowing")
    public String deleteShowing(HttpSession session, Model model, HttpServletRequest request, @ModelAttribute("showId") int showId) {
        getSession(model, session);
        User user = (User) session.getAttribute("loggedInUser");
        if (user.getAuthority().equals("EMPLOYEE")) {
            showRepository.deleteById(showId);
        }
        String referer = request.getHeader("referer");
        return "redirect:" + referer;
    }
}
