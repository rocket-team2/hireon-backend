package com.hireon.backend.Service;

import com.hireon.backend.Model.Company;
import com.hireon.backend.Repository.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepo companyRepo;

    public Company getCompany(Long id) {
        return companyRepo.findById(id).orElse(null);

    }

    public Company addCompany(Company company) {
        return companyRepo.save(company);
    }

    public Company updateCompany(Company company) {
        Company updateObj = getCompany(company.getComp_id());
        updateObj.setC_name(company.getC_name());
        updateObj.setComp_url(company.getComp_url());
        return companyRepo.save(updateObj);
    }

    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }
}
