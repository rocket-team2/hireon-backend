package com.hireon.backend.Model;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class User{
    private String name;
    private String email;
    private String password;
}
