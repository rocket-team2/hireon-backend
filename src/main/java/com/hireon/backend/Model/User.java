package com.hireon.backend.Model;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class User{
    private String name;
    private String email;
    private String password;
}
