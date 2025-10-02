package org.example.kino.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "Sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int saleID;
    @ManyToOne
    @JoinColumn(name = "snackID")
    private Snack snack;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User employee;

    public int getSaleID() {
        return saleID;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public Snack getSnack() {
        return snack;
    }

    public void setSnack(Snack snack) {
        this.snack = snack;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }



}