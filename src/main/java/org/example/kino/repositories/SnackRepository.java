package org.example.kino.repositories;

import com.g6.kinoxpnoname3s.model.Sale;
import com.g6.kinoxpnoname3s.model.Snack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnackRepository extends JpaRepository<Snack, Integer> {
}
