package com.reservationappbus.repository;



import com.reservationappbus.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusRepository extends JpaRepository<Bus, Long> {
}
