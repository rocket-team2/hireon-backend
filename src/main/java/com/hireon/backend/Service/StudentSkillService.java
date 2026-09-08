package com.hireon.backend.Service;

import com.hireon.backend.Model.Skill;
import com.hireon.backend.Model.Student;
import com.hireon.backend.Model.StudentSkill;
import com.hireon.backend.Repository.SkillRepo;
import com.hireon.backend.Repository.StudentRepo;
import com.hireon.backend.Repository.StudentSkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentSkillService {

    @Autowired
    private StudentSkillRepo studentSkillRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private SkillRepo skillRepo;

    public StudentSkill addSkill(long studentId, long skillId, double proficiency) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Skill skill = skillRepo.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
        StudentSkill studentSkill = new StudentSkill();
        studentSkill.setStudent(student);
        studentSkill.setSkill(skill);
        studentSkill.setProficiency(proficiency);
        return studentSkillRepo.save(studentSkill);
    }

    public List<StudentSkill> getStudentSkills(long studentId) {
        return studentSkillRepo.findByStudentId(studentId);
    }

    public StudentSkill updateProficiency(long studentId, long skillId, double proficiency) {
        StudentSkill studentSkill = studentSkillRepo
                .findByStudentAndSkill(studentId, skillId)
                .orElseThrow(() -> new RuntimeException("Student skill not found"));
        studentSkill.setProficiency(proficiency);
        return studentSkillRepo.save(studentSkill);
    }

    public void deleteSkill(long studentId, long skillId) {
        StudentSkill studentSkill = studentSkillRepo
                .findByStudentAndSkill(studentId, skillId)
                .orElseThrow(() -> new RuntimeException("Student skill not found"));
        studentSkillRepo.delete(studentSkill);
    }

    public List<Student> getStudentsBySkill(long skillId) {
        return studentSkillRepo.findBySkillId(skillId)
                .stream()
                .map(StudentSkill::getStudent)
                .collect(Collectors.toList());
    }
}
