package com.hireon.backend.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "drive_registration")
@Data
@NoArgsConstructor
public class DriveRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dr_id")
    private Long drId;

    @ManyToOne
    @JoinColumn(name = "drive_id", nullable = false)
    private Drive drive;

    @ManyToOne
    @JoinColumn(name = "s_id", nullable = false)
    private Student student;

    @Column(name = "reg_date")
    private LocalDateTime regDate;
}