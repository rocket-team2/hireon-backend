package com.hireon.backend.Model;

import jakarta.persistence.*;
@Entity
@Table(name="skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long skill_id;
    private String skill_name;
    public Skill() {
    }
    public Skill(String skill_name) {
        this.skill_name = skill_name;
    }
    public long getSkill_id() {
        return skill_id;
    }
    public String getSkill_name() {
        return skill_name;
    }
    public void setSkill_id(long skill_id) {
        this.skill_id = skill_id;
    }

    public void setSkill_name(String skill_name) {
        this.skill_name = skill_name;
    }
}