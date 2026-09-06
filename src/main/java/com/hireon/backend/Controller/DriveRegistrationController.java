package com.hireon.backend.Controller;

import com.hireon.backend.Model.DriveRegistration;
import com.hireon.backend.Service.DriveRegistrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class DriveRegistrationController {

    @Autowired
    private DriveRegistrationService service;



    @PostMapping("/drives/{driveId}/register")
    public DriveRegistration register(
            @PathVariable Long driveId,
            @RequestParam Long studentId) {

        return service.registerStudent(
                driveId,
                studentId
        );
    }


    @GetMapping("/drives/{driveId}/registrations")
    public List<DriveRegistration> getDriveRegistrations(
            @PathVariable Long driveId) {

        return service.getDriveRegistrations(driveId);
    }



    @GetMapping("/students/{studentId}/registrations")
    public List<DriveRegistration> getStudentRegistrations(
            @PathVariable Long studentId) {

        return service.getStudentRegistrations(studentId);
    }



    @DeleteMapping(
            "/drives/{driveId}/registrations/{studentId}"
    )
    public String deleteRegistration(
            @PathVariable Long driveId,
            @PathVariable Long studentId) {

        service.deleteRegistration(
                driveId,
                studentId
        );

        return "Registration deleted successfully";
    }
}