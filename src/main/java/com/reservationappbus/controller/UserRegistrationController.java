package com.reservationappbus.controller;


import com.reservationappbus.entity.UserRegistration;
import com.reservationappbus.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/user")
public class UserRegistrationController {

    @Autowired
    private UserServiceImpl usi;
    @PostMapping
    public UserRegistration createUser(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("file") MultipartFile profilePicture
    ) throws IOException {
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.setName(name);
        userRegistration.setEmail(email);
        userRegistration.setPassword(password);
        userRegistration.setProfilePicture(profilePicture.getBytes());
        UserRegistration user = usi.createUser(userRegistration);
        return user;
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserRegistration> getUserbyId(@PathVariable("id") long id){
        UserRegistration user =usi.getUserbyId(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
