package org.example.kino.repositories;

import com.g6.kinoxpnoname3s.model.Sale;
import com.g6.kinoxpnoname3s.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Integer> {
}
