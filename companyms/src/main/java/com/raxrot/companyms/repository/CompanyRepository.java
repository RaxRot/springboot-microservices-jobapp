package com.raxrot.companyms.repository;

import com.raxrot.companyms.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
