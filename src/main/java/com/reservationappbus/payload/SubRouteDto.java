package com.reservationappbus.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubRouteDto {

    private Long subRouteId;
    private String fromLocation;
    private String toLocation;
    private String fromDate;
    private String toDate;
    private String totalDuration;
    private String fromTime;
    private String toTime;
    private RouteDto route;
    private long busId;

    // Getters and setters
}
