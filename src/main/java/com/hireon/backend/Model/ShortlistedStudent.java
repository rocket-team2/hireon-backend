package com.hireon.backend.Model;

import com.hireon.backend.Enum.ShortlistStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "shortlisted_student")
public class ShortlistedStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shortlist_id")
    private Long shortlistId;

    @ManyToOne
    @JoinColumn(name = "round_id", nullable = false)
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties({"drive", "hibernateLazyInitializer", "handler"})
    private DriveRound round;

    @ManyToOne
    @JoinColumn(name = "s_id", nullable = false)
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties({"skills", "registrations", "hibernateLazyInitializer", "handler"})
    private Student student;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ShortlistStatus status;

    @Column(name = "feedback_url")
    private String feedbackUrl;
}