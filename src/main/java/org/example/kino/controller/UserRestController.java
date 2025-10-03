package org.example.kino.controller;

import jakarta.servlet.http.HttpSession;
import org.example.kino.model.User;
import org.example.kino.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserRestController {
    @Autowired
    private UserRepository userRepository;




    @PostMapping("/test")
    public String test() {
        return "It works!";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> body, HttpSession session) {
        String username = body.get("username");
        String password = body.get("password");

        return userRepository.findByUsernameAndPassword(username, password)
                .map(user -> {
                    session.setAttribute("user", user);
                    return ResponseEntity.ok("Login success");
                })
                .orElse(ResponseEntity.status(401).body("Invalid credentials"));
    }
    @PostMapping("/login1")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {
        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);
        if (user.isPresent()) {
            session.setAttribute("user", user.get());
            return "Login successful!";
        } else {
            return "Invalid credentials";
        }
    }


    @GetMapping("/me")
    public Object getSessionUser(HttpSession session) {
        return session.getAttribute("user");
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logged out!";
    }

    // CREATE
    @PostMapping("/createuser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User saved = userRepository.save(user);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // READ ALL
    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }



    // READ ONE by ID
    @GetMapping("/user{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/user{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        user.setUserID(id); // make sure the ID matches
        User updated = userRepository.save(user);
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/user{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
