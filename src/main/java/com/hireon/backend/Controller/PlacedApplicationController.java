package com.hireon.backend.Controller;

import com.hireon.backend.Model.PlacedApplication;
import com.hireon.backend.Service.PlacedApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/placed-applications")
@CrossOrigin
public class PlacedApplicationController {

    @Autowired
    private PlacedApplicationService service;

    @GetMapping
    public List<PlacedApplication> getAll() {
        return service.getAll();
    }

    @GetMapping("/student/{studentId}")
    public List<PlacedApplication> getByStudentId(@PathVariable Long studentId) {
        return service.getByStudentId(studentId);
    }

    @PostMapping
    public PlacedApplication createOrGet(@RequestBody PlacedApplication app) {
        return service.saveOrGet(app);
    }

    @PutMapping("/{id}/status")
    public PlacedApplication updateStatus(
            @PathVariable String id,
            @RequestParam String status) {
        return service.updateStatus(id, status);
    }
}
