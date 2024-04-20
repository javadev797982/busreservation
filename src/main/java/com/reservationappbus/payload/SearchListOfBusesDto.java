package com.reservationappbus.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchListOfBusesDto {
    private Long busId;
    private String busNumber;
    private double price;
    private String busType;
    private int totalSeats;
    private int availableSeats;
    private Long routeId;
    private String fromLocation;
    private String toLocation;
    private String fromDate;
    private String toDate;
    private String totalDuration;
    private String fromTime;
    private String toTime;
}
