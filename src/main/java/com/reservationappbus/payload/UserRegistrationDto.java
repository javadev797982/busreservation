package com.reservationappbus.payload;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import org.springframework.web.multipart.MultipartFile;

public class UserRegistrationDto {
    private long id;
    private String name;
    private String email;
    private String password;
    private byte[] profilePicture;

}
