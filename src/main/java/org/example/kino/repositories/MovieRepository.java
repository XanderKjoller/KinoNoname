package org.example.kino.repositories;

import com.g6.kinoxpnoname3s.model.Movie;
import com.g6.kinoxpnoname3s.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
