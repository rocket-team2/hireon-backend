package com.hireon.backend.Controller;

import com.hireon.backend.DTO.RequiredSkillRequest;
import com.hireon.backend.Model.RequiredSkills;
import com.hireon.backend.Service.RequiredSkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drives")
public class RequiredSkillController {
    @Autowired
    private RequiredSkillsService requiredSkillService;

    @PostMapping("/{driveId}/required-skills")
    public RequiredSkills addRequiedSkill(@PathVariable Long driveId,
            @RequestBody RequiredSkillRequest request) {
        return requiredSkillService.addRequiedSkills(driveId, request);
    }

    @GetMapping("/{driveId}/required-skills")
    public List<RequiredSkills> getAllRequiredSkill(@PathVariable Long driveId) {
        return requiredSkillService.getAllRequiredSkill(driveId);
    }

    @GetMapping("/all-required-skills")
    public List<RequiredSkills> getAllRequiredSkills() {
        return requiredSkillService.getAllRequiredSkills();
    }

    @PutMapping("/{driveId}/required-skills/{skillId}")
    public RequiredSkills updateRequiredSkill(
            @PathVariable Long driveId,
            @PathVariable Long skillId,
            @RequestBody RequiredSkillRequest request) {

        return requiredSkillService.updateRequiredSkill(
                driveId,
                skillId,
                request);
    }

    @DeleteMapping("/{driveId}/required-skills/{skillId}")
    public void deleteRequiredSkill(
            @PathVariable Long driveId,
            @PathVariable Long skillId) {

        requiredSkillService.deleteRequiredSkill(driveId, skillId);
    }

}
