package com.reservationappbus.controller;


import com.reservationappbus.payload.RouteDto;
import com.reservationappbus.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/route")
public class RouteController {
    @Autowired
    private RouteService routeService;
    @PostMapping
    public ResponseEntity<RouteDto> addRoute(@RequestBody RouteDto routeDto){
        RouteDto dto = routeService.addRoute(routeDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
