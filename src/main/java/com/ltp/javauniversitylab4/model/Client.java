package com.ltp.javauniversitylab4.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;
    private String name;
    private double balance;


    public Client(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public Client(Long clientId) {
        this.clientId = clientId;
    }

    public void decrementBalance(double balance) {
        this.balance -= balance;
    }
}
