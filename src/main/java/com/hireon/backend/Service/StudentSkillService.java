package com.hireon.backend.Service;

import com.hireon.backend.Model.Skill;
import com.hireon.backend.Model.Student;
import com.hireon.backend.Model.StudentSkill;
import com.hireon.backend.Repository.SkillRepo;
import com.hireon.backend.Repository.StudentRepo;
import com.hireon.backend.Repository.StudentSkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentSkillService {
    @Autowired
    private StudentSkillRepo studentSkillRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private SkillRepo skillRepo;

    public StudentSkill addSkill(long studentId, long skillId, double proficiency) {

        Student student = studentRepo.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        Skill skill = skillRepo.findById(skillId).orElseThrow(() -> new RuntimeException("Skill not found"));
        StudentSkill studentSkill = new StudentSkill();

        studentSkill.setStudent(student);
        studentSkill.setSkill(skill);
        studentSkill.setProficiency(proficiency);

        return studentSkillRepo.save(studentSkill);
    }

    public List<StudentSkill> getStudentSkills(long studentId) {
        List<StudentSkill> all = studentSkillRepo.findAll();
        List<StudentSkill> result = new ArrayList<>();

        for (StudentSkill studentSkill : all) {
            if (studentSkill.getStudent().getS_id() == studentId) {
                result.add(studentSkill);
            }
        }
        return result;
    }

    public StudentSkill updateProficiency(long studentId, long skillId, double proficiency) {
        List<StudentSkill> all = studentSkillRepo.findAll();
        for (StudentSkill studentSkill : all) {
            if (studentSkill.getStudent().getS_id() == studentId &&
                    studentSkill.getSkill().getSkill_id() == skillId) {
                studentSkill.setProficiency(proficiency);
                return studentSkillRepo.save(studentSkill);
            }
        }
        throw new RuntimeException("Student skill not found");
    }
    public void deleteSkill(long studentId, long skillId) {
        List<StudentSkill> all = studentSkillRepo.findAll();
        for (StudentSkill studentSkill : all) {
            if (studentSkill.getStudent().getS_id() == studentId &&
                    studentSkill.getSkill().getSkill_id() == skillId) {

                studentSkillRepo.delete(studentSkill);
                return;
            }
        }
        throw new RuntimeException("Student skill not found");
    }

    public List<Student> getStudentsBySkill(long skillId) {
        List<StudentSkill> all = studentSkillRepo.findAll();
        List<Student> result = new ArrayList<>();
        for (StudentSkill studentSkill : all) {
            if (studentSkill.getSkill().getSkill_id() == skillId) {
                result.add(studentSkill.getStudent());
            }
        }
        return result;
    }
}

