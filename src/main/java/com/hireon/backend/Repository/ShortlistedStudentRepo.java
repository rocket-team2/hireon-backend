
package com.hireon.backend.Repository;

import com.hireon.backend.Model.ShortlistedStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShortlistedStudentRepo
        extends JpaRepository<ShortlistedStudent, Long> {

    List<ShortlistedStudent> findByRoundId(Long roundId);

    List<ShortlistedStudent> findByStudentSId(Long sId);

    Optional<ShortlistedStudent> findByRoundIdAndStudentSId(
            Long roundId,
            Long sId
    );
}