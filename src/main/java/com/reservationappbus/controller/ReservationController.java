package com.reservationappbus.controller;



import com.reservationappbus.entity.Passenger;
import com.reservationappbus.repository.PassengerRepository;
import com.reservationappbus.service.ReservationService;
import com.reservationappbus.util.ExcelGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private ExcelGeneratorService excelGeneratorService;
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<?> bookTicket(
            @RequestParam long busId,
            @RequestParam long routeId,
            @RequestBody Passenger pasenger
    ){
        ResponseEntity<?> responseEntity = reservationService.bookTicket(busId, routeId, pasenger);
        return responseEntity;
    }
    @GetMapping("/passenger/excel")
    public ResponseEntity<Resource> generateExcelReport() {
        try {
            List<Passenger> passengers = passengerRepository.findAll();
            ByteArrayInputStream excelStream = excelGeneratorService.generateExcelReport(passengers);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=passenger-report.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(new InputStreamResource(excelStream));
        } catch (IOException e) {
            // Handle the exception, log, or throw a custom exception
            e.printStackTrace(); // Replace with your exception handling logic
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }}
