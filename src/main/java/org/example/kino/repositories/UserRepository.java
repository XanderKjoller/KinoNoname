package org.example.kino.repositories;

import com.g6.kinoxpnoname3s.model.Sale;
import com.g6.kinoxpnoname3s.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
