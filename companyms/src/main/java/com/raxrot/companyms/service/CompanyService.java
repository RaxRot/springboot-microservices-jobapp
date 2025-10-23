package com.raxrot.companyms.service;


import com.raxrot.companyms.model.Company;

import java.util.List;

public interface CompanyService {
    Company createCompany(Company company);
    List<Company> getCompanies();
    Company getCompanyById(Long id);
    Company updateCompany(Long id,Company company);
    void deleteCompany(Long id);
}
