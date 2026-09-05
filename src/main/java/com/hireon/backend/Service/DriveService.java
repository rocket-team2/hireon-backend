package com.hireon.backend.Service;

import com.hireon.backend.Model.Company;
import com.hireon.backend.Model.Director;
import com.hireon.backend.Model.Drive;
import com.hireon.backend.Repository.DriveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DriveService {
    @Autowired
    private DriveRepo driveRepo;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private DirectorService directorService;

    public Drive addDrive(Drive drive) {

        Company company =
                companyService.getCompany(
                        drive.getCompany().getComp_id()
                );

        Director director =
                directorService.getDirector(
                        drive.getDirector().getDirector_id()
                );

        drive.setCompany(company);
        drive.setDirector(director);

        return driveRepo.save(drive);
    }

    public List<Drive> getAllDrives() {
        return driveRepo.findAll();
    }

    public Drive getDrive(Long id) {
        return driveRepo.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Drive not found"
        ));
    }

    public List<Drive> getActiveDrives() {
        return driveRepo.findByDeadlineGreaterThanEqual(LocalDateTime.now());
    }

    public Drive updateDrive(Long id, Drive newDrive) {
            Drive existingDrive = driveRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Drive not found"));

            existingDrive.setJob_role(newDrive.getJob_role());
            existingDrive.setCtc_lpa(newDrive.getCtc_lpa());
            existingDrive.setMax_arrear(newDrive.getMax_arrear());
            existingDrive.setTarget_cg_batch(newDrive.getTarget_cg_batch());
            existingDrive.setDescription(newDrive.getDescription());
            existingDrive.setDeadline(newDrive.getDeadline());
            existingDrive.setAllowed_dept(newDrive.getAllowed_dept());

            return driveRepo.save(existingDrive);
    }
}
