package com.hireon.backend.DTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequiredSkillRequest {

    private Long skillId;
    private Double reqProficinecy ;
}