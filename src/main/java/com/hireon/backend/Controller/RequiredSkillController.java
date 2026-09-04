package com.hireon.backend.Controller;

import com.hireon.backend.DTO.RequiredSkillRequest;
import com.hireon.backend.Model.RequiredSkills;
import com.hireon.backend.Service.RequiredSkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drives")
public class RequiredSkillController {
    private  RequiredSkillsService requiredSkillService;

    @PostMapping("/{diveId}/required-skills")
    public RequiredSkillController addRequiedSkill(@PathVariable Long driveId,
                                                   @RequestBody RequiredSkillRequest request){
        return requiredSkillService.addRequiedSkill(driveId,request);
    }
}
