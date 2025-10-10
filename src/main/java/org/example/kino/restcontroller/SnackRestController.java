package org.example.kino.restcontroller;

import org.example.kino.model.Snack;
import org.example.kino.repositories.SnackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SnackRestController {
    @Autowired
    SnackRepository snackRepository;
    @PostMapping("/snacks")
    public List<Snack> snacks (){
        return snackRepository.findAll();
    }



    @PostMapping("/addSnack")
    public ResponseEntity<?> addSnack(@RequestBody Snack newSnack) {
        // Check if a snack with the same name already exists
        Snack existingSnack = snackRepository.findByName(newSnack.getName());
        if (existingSnack != null) {
            // Return HTTP 409 Conflict if the snack name already exists
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Snack with this name already exists");
        }

        // Create and save a new snack
        Snack snackToSave = new Snack();
        snackToSave.setName(newSnack.getName());
        snackToSave.setPrice(newSnack.getPrice());
        snackToSave.setImage(newSnack.getImage());

        snackRepository.save(snackToSave);

        // Return HTTP 201 Created with the saved snack
        return ResponseEntity.status(HttpStatus.CREATED).body(snackToSave);
    }
        @PutMapping("/snack/{id}")
    public ResponseEntity<?> updateSnack(@PathVariable int id, @RequestBody Snack updatedSnack) {
        // Find existing snack by ID
        Optional<Snack> existingSnackOptional = snackRepository.findById(id);

        // If snack not found → return 404
        if (existingSnackOptional.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Snack with ID " + id + " not found");
        }

        Snack existingSnack = existingSnackOptional.get();

        // Update the snack's details
        existingSnack.setName(updatedSnack.getName());
        existingSnack.setPrice(updatedSnack.getPrice());
        existingSnack.setImage(updatedSnack.getImage());

        // Save updated snack
        Snack savedSnack = snackRepository.save(existingSnack);

        // Return HTTP 200 OK with the updated snack
        return ResponseEntity.ok(savedSnack);
    }


}
