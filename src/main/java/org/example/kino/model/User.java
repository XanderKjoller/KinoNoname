package org.example.kino.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    private int userID;
    private String username;
    private String password;
    private String authority;
    private int bookingID;
}

