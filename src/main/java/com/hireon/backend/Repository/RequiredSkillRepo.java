package com.hireon.backend.Repository;
import com.hireon.backend.Model.RequiredSkills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RequiredSkillRepo extends JpaRepository<RequiredSkills,Long> {

    List<RequiredSkills> findByDriveId(Long driveId);

    Optional<RequiredSkills> findByDriveIdAndSkillId( Long driveId,Long skillId);
}
