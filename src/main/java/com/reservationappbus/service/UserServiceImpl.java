package com.reservationappbus.service;


import com.reservationappbus.entity.UserRegistration;
import com.reservationappbus.repository.UserRegistrationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class UserServiceImpl {

    @Autowired
    private UserRegistrationRepository urr;
    public UserRegistration createUser(UserRegistration userRegistration){
        UserRegistration user = urr.save(userRegistration);

        return user;
    }

    public UserRegistration getUserbyId(long id) {
        UserRegistration userRegistration = urr.findById(id).get();
        return userRegistration;
    }
}
