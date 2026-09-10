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
    private Long sId;
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
    @ManyToOne
    @JoinColumn(name = "comp_id")
    private Company company;

    public Long getSId() {
        return sId;
    }

    public void setSId(Long sId) {
        this.sId = sId;
    }

    public String getReg_no() {
        return reg_no;
    }

    public void setReg_no(String reg_no) {
        this.reg_no = reg_no;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getBatch_year() {
        return batch_year;
    }

    public void setBatch_year(Integer batch_year) {
        this.batch_year = batch_year;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getCgpa() {
        return cgpa;
    }

    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
    }

    public Boolean getIs_alumni() {
        return is_alumni;
    }

    public void setIs_alumni(Boolean is_alumni) {
        this.is_alumni = is_alumni;
    }

    public Integer getActive_arrear() {
        return active_arrear;
    }

    public void setActive_arrear(Integer active_arrear) {
        this.active_arrear = active_arrear;
    }

    public Integer getHistory_of_arrear() {
        return history_of_arrear;
    }

    public void setHistory_of_arrear(Integer history_of_arrear) {
        this.history_of_arrear = history_of_arrear;
    }

    public String getResume_url() {
        return resume_url;
    }

    public void setResume_url(String resume_url) {
        this.resume_url = resume_url;
    }

    public String getLinkedin_url() {
        return Linkedin_url;
    }

    public void setLinkedin_url(String linkedin_url) {
        Linkedin_url = linkedin_url;
    }

    public String getPlacement_status() {
        return placement_status;
    }

    public void setPlacement_status(String placement_status) {
        this.placement_status = placement_status;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
