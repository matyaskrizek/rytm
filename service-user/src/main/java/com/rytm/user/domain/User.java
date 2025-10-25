package com.rytm.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Long id;

    @Setter
    @Column(name = "username")
    private String username;

    @Setter
    @Column(name = "email")
    private String email;

    @Setter
    @Column(name = "profile_photo")
    private byte[] profilePhoto;

    @Setter
    @Column(name = "password")
    private String password;

}
