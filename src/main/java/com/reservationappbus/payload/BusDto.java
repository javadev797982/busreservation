package com.reservationappbus.payload;

//import com.reservationapp.Entity.Driver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusDto {

    private Long id;
    private String busNumber;
    private double price;
    private String busType;
    private int totalSeats;
    private int availableSeats;

}
