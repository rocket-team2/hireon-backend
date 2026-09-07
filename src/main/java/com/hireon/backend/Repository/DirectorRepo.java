package com.hireon.backend.Repository;

import com.hireon.backend.Model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DirectorRepo extends JpaRepository<Director, Long> {
    Optional<Director> findByEmail(String email);
}
