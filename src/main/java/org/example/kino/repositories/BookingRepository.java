package org.example.kino.repositories;

import com.g6.kinoxpnoname3s.model.Booking;
import com.g6.kinoxpnoname3s.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
