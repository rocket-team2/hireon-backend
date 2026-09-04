package com.hireon.backend.Model;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class User{
    private String name;
    private String email;
    private String password;
}
