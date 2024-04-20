package com.reservationappbus.controller;


import com.reservationappbus.payload.BusDto;
import com.reservationappbus.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;


@RestController
@RequestMapping("/api/v1/bus")
public class BusController {

    @Autowired
    private BusService busService;

    @PostMapping
    public ResponseEntity<String> addBus(@RequestBody BusDto busDto) throws ParseException {

        busService.addBus(busDto);
        return new ResponseEntity<>("Data saved",HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<?> getAllBuses(@RequestParam String fromLocation,
                                         @RequestParam String toLocation,
                                         @RequestParam String fromDate){
        ResponseEntity<?> allBuses = busService.getAllBuses(fromLocation, toLocation, fromDate);
        return new ResponseEntity<>(allBuses,HttpStatus.OK);
    }

}
