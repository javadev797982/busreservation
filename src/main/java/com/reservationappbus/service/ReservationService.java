package com.reservationappbus.service;

import com.itextpdf.text.DocumentException;

import com.reservationappbus.entity.Bus;
import com.reservationappbus.entity.Passenger;
import com.reservationappbus.entity.Route;
import com.reservationappbus.entity.SubRoute;
import com.reservationappbus.repository.BusRepository;
import com.reservationappbus.repository.PassengerRepository;
import com.reservationappbus.repository.RouteRepository;
import com.reservationappbus.repository.SubRouteRepository;
import com.reservationappbus.util.EmailService;
import com.reservationappbus.util.PdfTicketGeneratorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private BusRepository busRepository;
    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private PdfTicketGeneratorServiceImpl pdfTicketGeneratorService;

    @Autowired
    private SubRouteRepository subRouteRepository;
    @Autowired
    EmailService emailService;

    public ResponseEntity<?> bookTicket(long busId, long routeId, Passenger pasenger) {
        boolean busIsPresent = false;
        boolean routeIsPresent = false;
        boolean subRouteIsPresent = false;
        Optional<Bus> byId = busRepository.findById(busId);
        if (byId.isPresent()) {
            busIsPresent = true;
            Bus bus = byId.get();
        }
        Optional<Route> byRouteId = routeRepository.findById(routeId);
        if (byRouteId.isPresent()) {
            routeIsPresent = true;
            Bus bus = byId.get();
        }
        Optional<SubRoute> bySubRouteId = subRouteRepository.findById(routeId);
        if (byRouteId.isPresent()) {
            subRouteIsPresent = true;
            Bus bus = byId.get();
        }
        if (busIsPresent && routeIsPresent || busIsPresent && subRouteIsPresent) {
            Passenger p = new Passenger();
            p.setFirstName(pasenger.getFirstName());
            p.setLastName(pasenger.getLastName());
            p.setEmail(pasenger.getEmail());
            p.setMobile(pasenger.getMobile());
            p.setBusId(busId);
            p.setRouteId(routeId);
            Passenger savedPaasenger = passengerRepository.save(p);
            byte[] pdfBytes = new byte[0];
            try {
                pdfBytes = pdfTicketGeneratorService.generateTicket(savedPaasenger, byRouteId.get().getFromLocation(), byRouteId.get().getToLocation(), byRouteId.get().getFromDate(), byRouteId.get().getToDate());
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }
            emailService.sendTicketEmail(pasenger.getEmail(),"Booking Confirm", pdfBytes,"TicketInfo","Your reservation id "+savedPaasenger.getPassengerId());
        }
        return new ResponseEntity<>("Ticket Booked SuccessFully", HttpStatus.CREATED);
    }
}
