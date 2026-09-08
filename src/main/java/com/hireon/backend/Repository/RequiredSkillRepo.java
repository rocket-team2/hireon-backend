package com.hireon.backend.Repository;
import com.hireon.backend.Model.RequiredSkills;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RequiredSkillRepo extends JpaRepository<RequiredSkills,Long> {

    @EntityGraph(attributePaths = {"drive", "drive.company", "drive.director", "skill"})
    List<RequiredSkills> findByDrive_DriveId(Long driveId);

    @EntityGraph(attributePaths = {"drive", "drive.company", "drive.director", "skill"})
    Optional<RequiredSkills> findByDrive_DriveIdAndSkill_SkillId(
            Long driveId,
            Long skillId
    );
}
