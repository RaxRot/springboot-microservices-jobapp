package com.raxrot.companyms.service.impl;

import com.raxrot.companyms.exception.ApiException;
import com.raxrot.companyms.model.Company;
import com.raxrot.companyms.repository.CompanyRepository;
import com.raxrot.companyms.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ApiException("Company not found", HttpStatus.NOT_FOUND));
        return company;
    }

    @Override
    public Company updateCompany(Long id, Company company) {
        Company companyToUpdate = companyRepository.findById(id)
                .orElseThrow(() -> new ApiException("Company not found", HttpStatus.NOT_FOUND));
        companyToUpdate.setName(company.getName());
        companyToUpdate.setDescription(company.getDescription());
        Company updatedCompany = companyRepository.save(companyToUpdate);
        return updatedCompany;
    }

    @Override
    public void deleteCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ApiException("Company not found", HttpStatus.NOT_FOUND));
        companyRepository.delete(company);
    }
}
