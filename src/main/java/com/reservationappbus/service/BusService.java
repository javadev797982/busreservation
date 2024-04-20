package com.reservationappbus.service;


import com.reservationappbus.entity.Bus;
import com.reservationappbus.entity.Route;
import com.reservationappbus.entity.SubRoute;
import com.reservationappbus.payload.BusDto;
import com.reservationappbus.payload.SearchListOfBusesDto;
import com.reservationappbus.repository.BusRepository;
import com.reservationappbus.repository.DriverRepository;
import com.reservationappbus.repository.RouteRepository;
import com.reservationappbus.repository.SubRouteRepository;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BusService {
    @Autowired
    RouteRepository routeRepository;

    @Autowired
    SubRouteRepository subRouteRepository;

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private BusRepository busRepository;

    @Autowired
    private DriverRepository driverRepository;

    public ResponseEntity<?> getAllBuses(String fromLocation, String toLocation, String fromDate) {
        List<Route> routes = routeRepository.findByFromLocationAndToLocationAndFromDate(fromLocation, toLocation, fromDate);
        List<SubRoute> subRoute = subRouteRepository.findByFromLocationAndToLocationAndFromDate(fromLocation, toLocation, fromDate);
        List<SearchListOfBusesDto> searchDto = new ArrayList<>();
        if(routes!=null){
            for (Route route:routes){
                Bus bus = busRepository.findById(route.getBusId()).get();
                SearchListOfBusesDto searchListOfBusesDto = mapToSearchListOfBusesDto(bus, route);
                searchDto.add(searchListOfBusesDto);
            }
            return new ResponseEntity<>(searchDto, HttpStatus.OK);
        }
        if(subRoute!=null){
            for (SubRoute sRoute:subRoute){
                Bus bus = busRepository.findById(sRoute.getBusId()).get();
                SearchListOfBusesDto searchListOfBusesDto = mapToSearchListOfBusesDto(bus, sRoute);
                searchDto.add(searchListOfBusesDto);
            }
            return new ResponseEntity<>(searchDto, HttpStatus.OK);
        }
        return new ResponseEntity<>("No search found", HttpStatus.OK);
    }
    SearchListOfBusesDto mapToSearchListOfBusesDto(Bus bus, Route route){
        SearchListOfBusesDto searchDto = new SearchListOfBusesDto();

        // Mapping properties from Bus entity
        searchDto.setBusId(route.getBusId());
        searchDto.setBusNumber(bus.getBusNumber());
        searchDto.setPrice(bus.getPrice());
        searchDto.setBusType(bus.getBusType());
        searchDto.setTotalSeats(bus.getTotalSeats());
        searchDto.setAvailableSeats(bus.getAvailableSeats());

        // Mapping properties from Route entity
        searchDto.setRouteId(route.getId());
        searchDto.setFromLocation(route.getFromLocation());
        searchDto.setToLocation(route.getToLocation());
        searchDto.setFromDate(route.getFromDate());
        searchDto.setToDate(route.getToDate());
        searchDto.setTotalDuration(route.getTotalDuration());
        searchDto.setFromTime(route.getFromTime());
        searchDto.setToTime(route.getToTime());
        return searchDto;

    }
    SearchListOfBusesDto mapToSearchListOfBusesDto(Bus bus, SubRoute route){
        SearchListOfBusesDto searchDto = new SearchListOfBusesDto();

        // Mapping properties from Bus entity
        searchDto.setBusId(route.getBusId());
        searchDto.setBusNumber(bus.getBusNumber());
        searchDto.setPrice(bus.getPrice());
        searchDto.setBusType(bus.getBusType());
        searchDto.setTotalSeats(bus.getTotalSeats());
        searchDto.setAvailableSeats(bus.getAvailableSeats());

        // Mapping properties from Route entity
        searchDto.setRouteId(route.getBusId());
        searchDto.setFromLocation(route.getFromLocation());
        searchDto.setToLocation(route.getToLocation());
        searchDto.setFromDate(route.getFromDate());
        searchDto.setToDate(route.getToDate());
        searchDto.setTotalDuration(route.getTotalDuration());
        searchDto.setFromTime(route.getFromTime());
        searchDto.setToTime(route.getToTime());
        return searchDto;

    }

    public void addBus(BusDto busDto) {

        Bus bus = new Bus();
        bus.setBusNumber(busDto.getBusNumber());
        bus.setPrice(busDto.getPrice());
        bus.setBusType(busDto.getBusType());
        bus.setTotalSeats(busDto.getTotalSeats());
        bus.setAvailableSeats(busDto.getAvailableSeats());
        Bus savedBus = busRepository.save(bus);

    }
}
