package com.reservationappbus.repository;



import com.reservationappbus.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}
