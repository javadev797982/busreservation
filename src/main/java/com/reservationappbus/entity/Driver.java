package com.reservationappbus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverId;

    private String driverName;
    private String driverLicenseNumber;
    private String addNumber;
    private String address;
    private String contactNumber;
    private String alternateContactNumber;
    private String emailId;

}
