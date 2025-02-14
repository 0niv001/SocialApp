package com.social.socialapp.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

// User table from SQL Server

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String roles;

    @Column
    private String name;

    @Column
    private String bio;

    @Column
    private String profilePicture;


    /*
    @ElementCollection
    private Set<String> interests;

    @ElementCollection
    private Set<String> projects;

     */

    @ElementCollection
    private Set<String> friends;

    public UserEntity() {
    }
}
