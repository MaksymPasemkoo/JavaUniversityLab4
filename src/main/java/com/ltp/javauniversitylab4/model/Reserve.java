package com.ltp.javauniversitylab4.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
public class Reserve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reserveId;
    @OneToOne
    private House house;
    @OneToOne
    private Client client;
    private LocalDate date;

    public Reserve(House house, Client client, LocalDate date) {
        this.house = house;
        this.client = client;
        this.date = date;
    }

    public Reserve(Long reserveId) {
        this.reserveId = reserveId;
    }
}
