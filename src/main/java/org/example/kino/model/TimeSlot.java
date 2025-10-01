package org.example.kino.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
@Entity
public class TimeSlot {
    @Id
    private int slotID;
    private LocalDateTime start;
    private LocalDateTime end;
    private int userID;
    private String role;
}