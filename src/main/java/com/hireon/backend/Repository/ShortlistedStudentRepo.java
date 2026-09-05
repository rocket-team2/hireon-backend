package com.hireon.backend.Repository;

import com.hireon.backend.Model.ShortlistedStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShortlistedStudentRepo
        extends JpaRepository<ShortlistedStudent, Long> {

    List<ShortlistedStudent> findByRound_RoundId(Long roundId);

    List<ShortlistedStudent> findByStudent_SId(Long sId);

    Optional<ShortlistedStudent> findByRound_RoundIdAndStudent_SId(
            Long roundId,
            Long sId
    );
}