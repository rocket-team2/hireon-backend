package com.hireon.backend.Model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "drive", schema = "public")
public class Drive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drive_id")
    private Integer driveId;

    @Column(name = "comp_id", nullable = false)
    private Integer compId;

    @Column(name = "job_role", length = 100)
    private String jobRole;

    @Column(name = "ctc_lpa", precision = 10, scale = 2)
    private BigDecimal ctcLpa;

    @Column(name = "max_arrear")
    private Integer maxArrear;

    @Column(name = "target_cg_batch")
    private Integer targetCgBatch;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "description")
    private String description;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // PostgreSQL JSON column
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "allowed_dept", columnDefinition = "json")
    private List<String> allowedDept;


    // Getters and Setters

    public Integer getDriveId() {
        return driveId;
    }

    public void setDriveId(Integer driveId) {
        this.driveId = driveId;
    }

    public Integer getCompId() {
        return compId;
    }

    public void setCompId(Integer compId) {
        this.compId = compId;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public BigDecimal getCtcLpa() {
        return ctcLpa;
    }

    public void setCtcLpa(BigDecimal ctcLpa) {
        this.ctcLpa = ctcLpa;
    }

    public Integer getMaxArrear() {
        return maxArrear;
    }

    public void setMaxArrear(Integer maxArrear) {
        this.maxArrear = maxArrear;
    }

    public Integer getTargetCgBatch() {
        return targetCgBatch;
    }

    public void setTargetCgBatch(Integer targetCgBatch) {
        this.targetCgBatch = targetCgBatch;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<String> getAllowedDept() {
        return allowedDept;
    }

    public void setAllowedDept(List<String> allowedDept) {
        this.allowedDept = allowedDept;
    }
}