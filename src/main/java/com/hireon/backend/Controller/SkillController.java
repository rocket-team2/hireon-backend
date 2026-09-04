package com.hireon.backend.Controller;

import com.hireon.backend.Model.Skill;
import com.hireon.backend.Service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {
    @Autowired
    private SkillService skillService;

    @PostMapping("/createskills")
    public ResponseEntity<Skill> createSkill(@RequestBody Skill skill) {

        return ResponseEntity.ok(
                skillService.createSkill(skill)
        );
    }

    @GetMapping("/getskills")
    public ResponseEntity<List<Skill>> getAllSkills() {

        return ResponseEntity.ok(skillService.getAllSkills());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Skill> getSkillById(@PathVariable long id) {

        return ResponseEntity.ok(skillService.getSkillById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Skill> updateSkill(@PathVariable long id, @RequestBody Skill skill) {

        return ResponseEntity.ok(skillService.upadateSkill(id, skill));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteskill(@PathVariable long id) {
        skillService.deleteSkill(id);
        return ResponseEntity.ok("Skill deleted Successfully");
    }
}


