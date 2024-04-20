package com.reservationappbus.repository;

import com.reservationappbus.entity.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Long> {

}
