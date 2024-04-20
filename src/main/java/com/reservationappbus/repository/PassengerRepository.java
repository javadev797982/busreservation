package com.reservationappbus.repository;

import com.reservationappbus.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger,Long> {
}
