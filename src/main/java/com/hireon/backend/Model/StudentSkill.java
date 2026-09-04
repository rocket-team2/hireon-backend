package com.hireon.backend.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="student_skill")
public class StudentSkill {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long stuskill_id;
    @ManyToOne
    @JoinColumn(name="s_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name="skill_id")
    private Skill skill;
    private double proficiency;

    public StudentSkill(){

    }


}
