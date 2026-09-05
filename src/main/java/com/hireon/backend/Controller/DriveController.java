package com.hireon.backend.Controller;

import com.hireon.backend.Model.Drive;
import com.hireon.backend.Service.DriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("Drive")
public class DriveController {
    @Autowired
    private DriveService driveService;

    @PostMapping("/Add")
    public Drive addDrive(@RequestBody Drive drive) {
        drive.setCreated_At(LocalDateTime.now());
        return driveService.addDrive(drive);
    }

    @GetMapping()
    public List<Drive> getAllDrives() {
        return driveService.getAllDrives();
    }

    @GetMapping("/Id/{id}")
    public Drive getDrive(@PathVariable Long id) {
        return driveService.getDrive(id);
    }

    @GetMapping("ActiveDrives")
    public List<Drive> getActiveDrives() {
        return driveService.getActiveDrives();
    }

    @PutMapping("/Update/{id}")
    public Drive updateDrive(@PathVariable Long id, @RequestBody Drive drive) {
        return driveService.updateDrive(id, drive);
    }
}
