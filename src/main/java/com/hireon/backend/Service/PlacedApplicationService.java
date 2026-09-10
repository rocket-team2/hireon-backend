package com.hireon.backend.Service;

import com.hireon.backend.Model.PlacedApplication;
import com.hireon.backend.Repository.PlacedApplicationRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PlacedApplicationService {

    @Autowired
    private PlacedApplicationRepo repo;

    @Autowired(required = false)
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DriveRegistrationService driveRegistrationService;

    // In-memory fallback cache to ensure zero data loss
    private final ConcurrentHashMap<String, PlacedApplication> cache = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        if (jdbcTemplate != null) {
            try {
                jdbcTemplate.execute(
                    "CREATE TABLE IF NOT EXISTS placed_applications (" +
                    "id VARCHAR(100) PRIMARY KEY, " +
                    "drive_id BIGINT NOT NULL, " +
                    "drive_title VARCHAR(255), " +
                    "company_name VARCHAR(255), " +
                    "student_id BIGINT NOT NULL, " +
                    "student_name VARCHAR(255), " +
                    "student_reg_no VARCHAR(100), " +
                    "student_dept VARCHAR(100), " +
                    "student_cgpa DOUBLE PRECISION, " +
                    "placed_company VARCHAR(255), " +
                    "status VARCHAR(50) NOT NULL, " +
                    "requested_at VARCHAR(100)" +
                    ")"
                );
            } catch (Exception e) {
                System.err.println("Notice: Placed applications table init: " + e.getMessage());
            }
        }
    }

    public List<PlacedApplication> getAll() {
        try {
            List<PlacedApplication> fromDb = repo.findAll();
            for (PlacedApplication app : fromDb) {
                cache.put(app.getId(), app);
            }
            return new ArrayList<>(cache.values());
        } catch (Exception e) {
            return new ArrayList<>(cache.values());
        }
    }

    public List<PlacedApplication> getByStudentId(Long studentId) {
        try {
            List<PlacedApplication> fromDb = repo.findByStudentId(studentId);
            if (!fromDb.isEmpty()) {
                return fromDb;
            }
        } catch (Exception ignored) {
        }
        List<PlacedApplication> matching = new ArrayList<>();
        for (PlacedApplication app : cache.values()) {
            if (studentId.equals(app.getStudentId())) {
                matching.add(app);
            }
        }
        return matching;
    }

    public PlacedApplication saveOrGet(PlacedApplication app) {
        if (app.getId() == null || app.getId().isBlank()) {
            app.setId("req_" + System.currentTimeMillis() + "_" + (int)(Math.random() * 1000));
        }
        if (app.getStatus() == null || app.getStatus().isBlank()) {
            app.setStatus("PENDING");
        }

        // Check if already exists for this drive and student
        try {
            Optional<PlacedApplication> existing = repo.findByDriveIdAndStudentId(app.getDriveId(), app.getStudentId());
            if (existing.isPresent()) {
                cache.put(existing.get().getId(), existing.get());
                return existing.get();
            }
        } catch (Exception ignored) {
        }

        for (PlacedApplication cached : cache.values()) {
            if (app.getDriveId().equals(cached.getDriveId()) && app.getStudentId().equals(cached.getStudentId())) {
                return cached;
            }
        }

        cache.put(app.getId(), app);
        try {
            return repo.save(app);
        } catch (Exception e) {
            System.err.println("Notice: Could not save placed application to DB, using cache: " + e.getMessage());
            return app;
        }
    }

    public PlacedApplication updateStatus(String id, String status) {
        PlacedApplication app = cache.get(id);
        try {
            Optional<PlacedApplication> fromDb = repo.findById(id);
            if (fromDb.isPresent()) {
                app = fromDb.get();
            }
        } catch (Exception ignored) {
        }

        if (app != null) {
            app.setStatus(status);
            cache.put(id, app);
            try {
                repo.save(app);
            } catch (Exception ignored) {
            }

            // If APPROVED, auto register student in drive in backend
            if ("APPROVED".equalsIgnoreCase(status)) {
                try {
                    driveRegistrationService.registerStudent(app.getDriveId(), app.getStudentId());
                } catch (Exception e) {
                    System.out.println("Note: Student registration in drive: " + e.getMessage());
                }
            }
        }

        return app;
    }
}
