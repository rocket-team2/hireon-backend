package com.hireon.backend.DTO;

import lombok.Data;
import java.util.List;

@Data
public class ProcessRoundRequest {
    private List<String> regNos;
}
