package com.hireon.backend.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="director")
@Data
@NoArgsConstructor
public class Director extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long director_id;


}
