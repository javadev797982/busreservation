package com.reservationappbus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bus_number", unique = true, nullable = false)
    private String busNumber;
    private double price;
    private String busType;
    private int totalSeats;
    private int availableSeats;

}
