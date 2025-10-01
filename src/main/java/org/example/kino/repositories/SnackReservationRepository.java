package org.example.kino.repositories;

import org.example.kino.model.Snack;
import org.example.kino.model.SnackReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnackReservationRepository extends JpaRepository<SnackReservation, Integer> {
}
