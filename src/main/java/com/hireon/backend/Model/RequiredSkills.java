package com.hireon.backend.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name = "required_skills")
@Data
@NoArgsConstructor
public class RequiredSkills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long req_id;

    @ManyToOne
    @JoinColumn(name="dirve_id")
    private  Drive dirve;

    @ManyToOne
    @JoinColumn(name="skill_id")
    private Skill skill;

    private Integer reqProficiency;




}


