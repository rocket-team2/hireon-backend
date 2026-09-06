package com.hireon.backend.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DriveRoundRequest {

    private String roundName;

    private String roundLink;

    private boolean isFinal;

    private String description;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}