package com.hireon.backend.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "drive_round")
@Data
@NoArgsConstructor
public class DriveRound {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "round_id")
    private Long roundId;

    @ManyToOne
    @JoinColumn(name = "drive_id", nullable = false)
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties({"rounds", "requiredSkills", "registrations", "hibernateLazyInitializer", "handler"})
    private Drive drive;

    @Column(name = "round_name")
    private String roundName;

    @Column(name = "round_link")
    private String roundLink;

    @Column(name = "is_final")
    private boolean isFinal;

    @Column(name = "description")
    private String description;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;
}