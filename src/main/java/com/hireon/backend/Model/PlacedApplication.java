package com.hireon.backend.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "placed_applications")
public class PlacedApplication {

    @Id
    @Column(name = "id", length = 100)
    private String id;

    @Column(name = "drive_id", nullable = false)
    private Long driveId;

    @Column(name = "drive_title")
    private String driveTitle;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "student_reg_no")
    private String studentRegNo;

    @Column(name = "student_dept")
    private String studentDept;

    @Column(name = "student_cgpa")
    private Double studentCgpa;

    @Column(name = "placed_company")
    private String placedCompany;

    @Column(name = "status", nullable = false)
    private String status; // PENDING, APPROVED, REJECTED

    @Column(name = "requested_at")
    private String requestedAt;

    public PlacedApplication() {}

    public PlacedApplication(String id, Long driveId, String driveTitle, String companyName,
                            Long studentId, String studentName, String studentRegNo,
                            String studentDept, Double studentCgpa, String placedCompany,
                            String status, String requestedAt) {
        this.id = id;
        this.driveId = driveId;
        this.driveTitle = driveTitle;
        this.companyName = companyName;
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentRegNo = studentRegNo;
        this.studentDept = studentDept;
        this.studentCgpa = studentCgpa;
        this.placedCompany = placedCompany;
        this.status = status;
        this.requestedAt = requestedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getDriveId() {
        return driveId;
    }

    public void setDriveId(Long driveId) {
        this.driveId = driveId;
    }

    public String getDriveTitle() {
        return driveTitle;
    }

    public void setDriveTitle(String driveTitle) {
        this.driveTitle = driveTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentRegNo() {
        return studentRegNo;
    }

    public void setStudentRegNo(String studentRegNo) {
        this.studentRegNo = studentRegNo;
    }

    public String getStudentDept() {
        return studentDept;
    }

    public void setStudentDept(String studentDept) {
        this.studentDept = studentDept;
    }

    public Double getStudentCgpa() {
        return studentCgpa;
    }

    public void setStudentCgpa(Double studentCgpa) {
        this.studentCgpa = studentCgpa;
    }

    public String getPlacedCompany() {
        return placedCompany;
    }

    public void setPlacedCompany(String placedCompany) {
        this.placedCompany = placedCompany;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(String requestedAt) {
        this.requestedAt = requestedAt;
    }
}
