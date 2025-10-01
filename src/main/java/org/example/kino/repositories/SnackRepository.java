package org.example.kino.repositories;


import org.example.kino.model.Snack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnackRepository extends JpaRepository<Snack, Integer> {
}
