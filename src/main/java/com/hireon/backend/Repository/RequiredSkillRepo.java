package com.hireon.backend.Repository;

import com.hireon.backend.Model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequiredSkillRepo extends JpaRepository<Director, Long> {
}
