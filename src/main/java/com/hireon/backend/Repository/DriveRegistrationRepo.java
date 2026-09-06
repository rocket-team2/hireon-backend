package com.hireon.backend.Repository;

import com.hireon.backend.Model.DriveRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriveRegistrationRepo
        extends JpaRepository<DriveRegistration, Long> {
}