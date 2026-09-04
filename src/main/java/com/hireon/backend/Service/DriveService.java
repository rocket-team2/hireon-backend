package com.hireon.backend.Service;
import com.hireon.backend.Model.Drive;
import com.hireon.backend.Repository.DriveRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDateTime;

@Service
public class DriveService {
    private final DriveRepo driveRepository;
    public DriveService(DriveRepo driveRepository){
        this.driveRepository=driveRepository;
    }
    public Drive createDrive(Drive drive){
        drive.setCreatedAt(LocalDateTime.now());
        return driveRepository.save(drive);
    }
    public List<Drive> getAllDrives(){
        return driveRepository.findAll();
    }
    public Drive getDriveById(Integer id){
        return driveRepository.findById(id).
            orElseThrow(()->new RuntimeException("Drive not found with id: "+id));
    }
    public Drive updateDrive(Integer id,Drive updatedDrive){
        Drive existingDrive=driveRepository.findById(id).
            orElseThrow(()->new RuntimeException("Drive not found with id: "+id));
        existingDrive.setCompId(updatedDrive.getCompId());
        existingDrive.setJobRole(updatedDrive.getJobRole());
        existingDrive.setCtcLpa(updatedDrive.getCtcLpa());
        existingDrive.setMaxArrear(updatedDrive.getMaxArrear());
        existingDrive.setTargetCgBatch(updatedDrive.getTargetCgBatch());
        existingDrive.setDescription(updatedDrive.getDescription());
        existingDrive.setDeadline(updatedDrive.getDeadline());
        existingDrive.setAllowedDept(updatedDrive.getAllowedDept());
        return driveRepository.save(existingDrive);
    }
    public void deleteDrive(Integer id){
        if(!driveRepository.existsById(id)){
            throw new RuntimeException("Drive not found with id: "+id);
        }
        driveRepository.deleteById(id);
    }
    }

