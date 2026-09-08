package com.hireon.backend.Repository;

import com.hireon.backend.Model.StudentSkill;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentSkillRepo extends JpaRepository<StudentSkill, Long> {

    @EntityGraph(attributePaths = {"student", "student.company", "skill"})
    @Query("SELECT ss FROM StudentSkill ss WHERE ss.student.sId = :studentId")
    List<StudentSkill> findByStudentId(@Param("studentId") Long studentId);

    @EntityGraph(attributePaths = {"student", "student.company", "skill"})
    @Query("SELECT ss FROM StudentSkill ss WHERE ss.student.sId = :studentId AND ss.skill.skillId = :skillId")
    Optional<StudentSkill> findByStudentAndSkill(@Param("studentId") Long studentId, @Param("skillId") Long skillId);

    @EntityGraph(attributePaths = {"student", "student.company", "skill"})
    @Query("SELECT ss FROM StudentSkill ss WHERE ss.skill.skillId = :skillId")
    List<StudentSkill> findBySkillId(@Param("skillId") Long skillId);
}
