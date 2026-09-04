package com.hireon.backend.Controller;
import com.hireon.backend.Model.Drive;
import com.hireon.backend.Service.DriveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/drives")
public class DriveController {
    private final DriveService driveService;
    public DriveController(DriveService driveService){
        this.driveService=driveService;
    }
    @PostMapping
    public ResponseEntity<Drive> createDrive(@RequestBody Drive drive){
        Drive createdDrive=driveService.createDrive(drive);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDrive);
    }
    @GetMapping
    public ResponseEntity<List<Drive>> getAllDrives(){
        List<Drive> drives=driveService.getAllDrives();
        return ResponseEntity.ok(drives);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Drive> getDriveById(
        @PathVariable Integer id) {
    Drive drive = driveService.getDriveById(id);
    return ResponseEntity.ok(drive);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Drive> updateDrive(
            @PathVariable Integer id,
            @RequestBody Drive drive
    ){
        Drive updatedDrive=driveService.updateDrive(id,drive);
        return ResponseEntity.ok(updatedDrive);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrive(
            @PathVariable Integer id) {
        driveService.deleteDrive(id);
        return ResponseEntity.noContent().build();
    }
}
