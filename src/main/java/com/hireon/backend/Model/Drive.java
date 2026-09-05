package com.hireon.backend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import tools.jackson.databind.JsonNode;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driveId;
    @ManyToOne
    @JoinColumn(name="comp_id")
    private Company company;
    private String job_role;
    private int ctc_lpa;
    private int max_arrear;
    private int target_cg_batch;
    @ManyToOne
    @JoinColumn(name = "created_by")
    private Director director;
    private String description;
    private LocalDateTime deadline;
    private LocalDateTime created_At;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private JsonNode allowed_dept;
}
