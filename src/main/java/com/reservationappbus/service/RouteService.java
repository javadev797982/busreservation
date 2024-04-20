package com.reservationappbus.service;


import com.reservationappbus.entity.Bus;
import com.reservationappbus.entity.Route;
import com.reservationappbus.exception.ResourceNotFoundException;
import com.reservationappbus.payload.RouteDto;
import com.reservationappbus.repository.BusRepository;
import com.reservationappbus.repository.RouteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    BusRepository busRepository;
    @Autowired
    RouteRepository routeRepository;
    public RouteDto addRoute(RouteDto routeDto) {
        Bus bus = busRepository.findById(routeDto.getBusId()).orElseThrow(() -> new ResourceNotFoundException("Bus not added!"));
        Route r = routeRepository.findByBusId(routeDto.getBusId());
        if(r!=null){
            throw new ResourceNotFoundException("Route is already added");
        }
        if(r==null){
            Route route = modelMapper.map(routeDto, Route.class);
            Route r1 = routeRepository.save(route);
            RouteDto dto = modelMapper.map(r1, RouteDto.class);
            return dto;
        }
        return null;
    }
}
