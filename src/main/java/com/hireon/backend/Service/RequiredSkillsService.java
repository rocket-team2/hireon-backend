package com.hireon.backend.Service;

import com.hireon.backend.Controller.RequiredSkillController;
import com.hireon.backend.DTO.RequiredSkillRequest;
import com.hireon.backend.Model.Drive;
import com.hireon.backend.Model.RequiredSkills;
import com.hireon.backend.Repository.DriveRepo;
import com.hireon.backend.Repository.RequiredSkillRepo;
import com.hireon.backend.Repository.SkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequiredSkillsService {
    @Autowired
    private RequiredSkillRepo requiredSkillRepo;


    @Autowired
    private DriveRepo driveRepo;

    @Autowired
    private SkillRepo skillRepo;


    public List<RequiredSkills> getAllRequiredSkill(Long driveId) {

        return requiredSkillRepo.findByDriveId(driveId);
    }

    public RequiredSkills updateRequiredSkill(
            Long  dirveId,
            Long  skillId,
            RequiredSkillRequest request){
        RequiredSkills requiredSkills=requiredSkillRepo.findByDriveIdAndSkillId(dirveId,skillId)
                .orElseThrow(()-> new RuntimeException("Requied skill not found"));
        requiredSkills.setReqProficiency(request.getReqProficinecy());
        return requiredSkillRepo.save(requiredSkills);
    }

    public void deleteRequiredSkill(Long driveId, Long skillId, RequiredSkillRequest request) {
        RequiredSkills requiredSkills=requiredSkillRepo.findByDriveIdAndSkillId(driveId,skillId)
                .orElseThrow(()-> new RuntimeException("Requied skill not found"));
        requiredSkills.setReqProficiency(request.getReqProficinecy());
         requiredSkillRepo.delete(requiredSkills);
    }

    public RequiredSkills addRequiedSkills(Long driveId, RequiredSkillRequest request) {

        Drive drive  =driveRepo.findById(driveId).
                orElseThrow(()->new RuntimeException("Drive not found"));

        Drive skill= driveRepo.findById(request.getSkillId())
                .orElseThrow(()->new RuntimeException("Drive not found"));



        RequiredSkills requiredSkill = new RequiredSkills();

        requiredSkill.setDrive(drive);
        requiredSkill.setSkill(skill);
        requiredSkill.setReqProficiency(request.getReqProficinecy());


        return requiredSkillRepo.save(requiredSkill);


    }
}
