package com.hireon.backend.Repository;

import com.hireon.backend.Model.DriveRegistration;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DriveRegistrationRepo extends JpaRepository<DriveRegistration, Long> {

    @EntityGraph(attributePaths = {
            "student", "student.company",
            "drive", "drive.company", "drive.director"
    })
    @Query("SELECT r FROM DriveRegistration r WHERE r.drive.driveId = :driveId")
    List<DriveRegistration> findByDriveId(@Param("driveId") Long driveId);

    @EntityGraph(attributePaths = {
            "student", "student.company",
            "drive", "drive.company", "drive.director"
    })
    @Query("SELECT r FROM DriveRegistration r WHERE r.student.sId = :studentId")
    List<DriveRegistration> findByStudentId(@Param("studentId") Long studentId);

    boolean existsByDrive_DriveIdAndStudent_SId(Long driveId, Long studentId);

    Optional<DriveRegistration> findByDrive_DriveIdAndStudent_SId(Long driveId, Long studentId);
}
