package org.example.kino.repositories;

import com.g6.kinoxpnoname3s.model.Sale;
import com.g6.kinoxpnoname3s.model.SnackReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnackReservationRepository extends JpaRepository<SnackRepository, Integer> {
}
