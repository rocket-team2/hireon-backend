package com.hireon.backend.Service;

import com.hireon.backend.Model.Skill;
import com.hireon.backend.Repository.SkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SkillService {
    @Autowired
    private SkillRepo skillRepository;

    public Skill createSkill(Skill skill) {

            return skillRepository.save(skill);

    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Skill getSkillById(long id) {
        return skillRepository.findById(id).orElseThrow(() -> new RuntimeException("Skill not found"));

    }

    public Skill upadateSkill(long id, Skill newskill) {
        Skill skill=skillRepository.findById(id).orElseThrow(() -> new RuntimeException("Skill not found"));
        skill.setSkill_name(newskill.getSkill_name());
        return skillRepository.save(skill);
    }

    public void deleteSkill(long id) {
        Skill skill=skillRepository.findById(id).orElseThrow(() -> new RuntimeException("Skill not found"));
        skillRepository.delete(skill);
    }
}