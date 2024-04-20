package com.reservationappbus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_registrations")
public class UserRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String password;
    @Lob //This is the annotaion for image uploading
    @Column(name="profile_picture", length = 1024)
    private byte[] profilePicture;

}
