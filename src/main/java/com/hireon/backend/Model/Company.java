package com.hireon.backend.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comp_id;
    private String c_name;
    private String comp_url;

    public Long getComp_id() {
        return comp_id;
    }

    public void setComp_id(Long comp_id) {
        this.comp_id = comp_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getComp_url() {
        return comp_url;
    }

    public void setComp_url(String comp_url) {
        this.comp_url = comp_url;
    }
}
