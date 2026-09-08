package com.hireon.backend.Service;

import com.hireon.backend.Model.Drive;
import com.hireon.backend.Model.DriveRegistration;
import com.hireon.backend.Model.Student;
import com.hireon.backend.Repository.DriveRegistrationRepo;
import com.hireon.backend.Repository.DriveRepo;
import com.hireon.backend.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DriveRegistrationService {

    @Autowired
    private DriveRegistrationRepo driveRegistrationRepo;

    @Autowired
    private DriveRepo driveRepo;

    @Autowired
    private StudentRepo studentRepo;

    public DriveRegistration registerStudent(Long driveId, Long studentId) {
        if (driveRegistrationRepo.existsByDriveAndStudent(driveId, studentId)) {
            throw new RuntimeException("Student already registered for this drive");
        }

        Drive drive = driveRepo.findById(driveId)
                .orElseThrow(() -> new RuntimeException("Drive not found"));

        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        DriveRegistration registration = new DriveRegistration();
        registration.setDrive(drive);
        registration.setStudent(student);
        registration.setRegDate(LocalDateTime.now());

        return driveRegistrationRepo.save(registration);
    }

    public List<DriveRegistration> getDriveRegistrations(Long driveId) {
        return driveRegistrationRepo.findByDriveId(driveId);
    }

    public List<DriveRegistration> getStudentRegistrations(Long studentId) {
        return driveRegistrationRepo.findByStudentId(studentId);
    }

    public void deleteRegistration(Long driveId, Long studentId) {
        DriveRegistration registration = driveRegistrationRepo
                .findByDriveAndStudent(driveId, studentId)
                .orElseThrow(() -> new RuntimeException("Registration not found"));
        driveRegistrationRepo.delete(registration);
    }
}
