package com.hireon.backend.Repository;

import com.hireon.backend.Model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<Company, Long> {
}
