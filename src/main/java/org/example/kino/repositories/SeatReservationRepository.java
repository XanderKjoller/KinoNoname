package org.example.kino.repositories;

import org.example.kino.model.SeatReservation;
import org.example.kino.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface SeatReservationRepository extends JpaRepository<SeatReservation, Integer> {
}
