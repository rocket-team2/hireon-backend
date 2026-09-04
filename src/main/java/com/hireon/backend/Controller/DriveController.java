package com.hireon.backend.Controller;
import com.hireon.backend.Model.Drive;
import com.hireon.backend.Service.DriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Drives")
public class DriveController {
    @Autowired
    private DriveService driveService;
}
