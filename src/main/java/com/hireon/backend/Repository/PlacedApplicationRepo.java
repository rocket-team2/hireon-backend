package com.hireon.backend.Repository;

import com.hireon.backend.Model.PlacedApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlacedApplicationRepo extends JpaRepository<PlacedApplication, String> {
    List<PlacedApplication> findByStudentId(Long studentId);
    Optional<PlacedApplication> findByDriveIdAndStudentId(Long driveId, Long studentId);
}
