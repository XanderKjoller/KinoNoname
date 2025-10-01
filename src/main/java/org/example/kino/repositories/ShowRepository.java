package org.example.kino.repositories;

import com.g6.kinoxpnoname3s.model.Sale;
import com.g6.kinoxpnoname3s.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Integer> {
}
