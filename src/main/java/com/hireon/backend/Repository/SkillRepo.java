package com.hireon.backend.Repository;

import com.hireon.backend.Model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepo extends JpaRepository<Skill, Long> {

}