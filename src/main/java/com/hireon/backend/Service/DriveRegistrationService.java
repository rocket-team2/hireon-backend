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
import java.util.ArrayList;
import java.util.List;

@Service
public class DriveRegistrationService {

    @Autowired
    private DriveRegistrationRepo driveRegistrationRepo;

    @Autowired
    private DriveRepo driveRepo;

    @Autowired
    private StudentRepo studentRepo;



    public DriveRegistration registerStudent(
            Long driveId,
            Long studentId) {

        Drive drive = driveRepo.findById(driveId)
                .orElseThrow(() ->
                        new RuntimeException("Drive not found"));

        Student student = studentRepo.findById(studentId)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));



        List<DriveRegistration> all =
                driveRegistrationRepo.findAll();

        for (DriveRegistration registration : all) {

            if (registration.getDrive().getDriveId().equals(driveId)
                    && registration.getStudent().getSId().equals(studentId)) {


                throw new RuntimeException(
                        "Student already registered for this drive");
            }
        }


        DriveRegistration registration =
                new DriveRegistration();

        registration.setDrive(drive);
        registration.setStudent(student);
        registration.setRegDate(LocalDateTime.now());

        return driveRegistrationRepo.save(registration);
    }


    // GET REGISTRATIONS FOR DRIVE
    public List<DriveRegistration> getDriveRegistrations(
            Long driveId) {

        List<DriveRegistration> all =
                driveRegistrationRepo.findAll();

        List<DriveRegistration> result =
                new ArrayList<>();

        for (DriveRegistration registration : all) {

            if (registration.getDrive()
                    .getDriveId()
                    .equals(driveId)) {

                result.add(registration);
            }
        }

        return result;
    }



    public List<DriveRegistration> getStudentRegistrations(
            Long studentId) {

        List<DriveRegistration> all =
                driveRegistrationRepo.findAll();

        List<DriveRegistration> result =
                new ArrayList<>();

        for (DriveRegistration registration : all) {

            if (registration.getStudent()
                    .getSId()
                    .equals(studentId)) {

                result.add(registration);
            }
        }

        return result;
    }



    public void deleteRegistration(
            Long driveId,
            Long studentId) {

        List<DriveRegistration> all =
                driveRegistrationRepo.findAll();

        for (DriveRegistration registration : all) {

            if (registration.getDrive()
                    .getDriveId()
                    .equals(driveId)
                    &&
                    registration.getStudent()
                            .getSId()
                            .equals(studentId)) {

                driveRegistrationRepo.delete(registration);
                return;
            }
        }

        throw new RuntimeException(
                "Registration not found");
    }
}