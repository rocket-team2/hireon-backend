package com.hireon.backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Student extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long s_id;
    private String reg_no;
    private String department;
    private Integer batch_year;
    private String role;
    private Double cgpa;
    private Boolean is_alumni;
    private Integer active_arrear;
    private Integer history_of_arrear;
    private String resume_url;
    private String Linkedin_url;
    private String placement_status;
    private Long comp_id;
//    @ManyToOne
//    @JoinColumn(name = "comp_id")
//    private Company company;
}
