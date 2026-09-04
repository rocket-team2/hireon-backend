package com.hireon.backend.Controller;

import com.hireon.backend.Model.Student;
import com.hireon.backend.Model.StudentSkill;
import com.hireon.backend.Service.StudentSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/studentskill")
public class StudentSkillController {

    @Autowired
    private StudentSkillService studentSkillService;

    @PostMapping("/{s_id}/skills")
    public ResponseEntity<StudentSkill> addSkill(@PathVariable long s_id, @RequestBody StudentSkill studentSkill) {
        StudentSkill result = studentSkillService.addSkill(s_id, studentSkill.getSkill().getSkill_id(), studentSkill.getProficiency());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{s_id}/skills")
    public ResponseEntity<List<StudentSkill>> getStudentSkills(@PathVariable long s_id) {
        return ResponseEntity.ok(studentSkillService.getStudentSkills(s_id));
    }
    @PutMapping("/{s_id}/skills/{skill_id}")
    public ResponseEntity<StudentSkill> updateProficiency(@PathVariable long s_id, @PathVariable long skill_id, @RequestBody StudentSkill studentSkill) {
        StudentSkill result = studentSkillService.updateProficiency(s_id, skill_id, studentSkill.getProficiency());
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/{s_id}/skills/{skill_id}")
    public ResponseEntity<String> deleteSkill(@PathVariable long s_id, @PathVariable long skill_id) {
        studentSkillService.deleteSkill(s_id, skill_id);
        return ResponseEntity.ok("Skill removed from student");
    }
    @GetMapping("/skills/{skill_id}/students")
    public ResponseEntity<List<Student>> getStudentsBySkill(@PathVariable long skill_id) {
        return ResponseEntity.ok(
                studentSkillService.getStudentsBySkill(skill_id));
    }
}