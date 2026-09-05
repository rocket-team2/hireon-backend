package com.hireon.backend.Repository;

import com.hireon.backend.Model.Drive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DriveRepo extends JpaRepository<Drive, Long> {
        List<Drive> findByDeadlineGreaterThanEqual(LocalDateTime now);
}
