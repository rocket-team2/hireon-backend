package com.hireon.backend.DTO;

import lombok.Data;

import java.util.List;

@Data
public class AdvanceRoundRequest {

    private Long toRoundId;
    private List<String> regNos;
}
