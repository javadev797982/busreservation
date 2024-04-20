package com.reservationappbus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="passengers")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long passengerId;
    private String firstName;
    private String lastName;
    private String email;
    private long mobile;
    @Column(name = "bus_id", unique = true, nullable = false)
    private long busId;
    @Column(name="route_id", unique = true, nullable = false)
    private long routeId;
}
