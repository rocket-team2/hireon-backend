package com.hireon.backend.Controller;

import com.hireon.backend.Model.Company;
import com.hireon.backend.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("Id/{id}")
    public Company getCompany(@PathVariable Long id) {
        return companyService.getCompany(id);
    }

    @GetMapping()
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @PostMapping("/Add")
    public Company addCompany(@RequestBody Company company) {
        return companyService.addCompany(company);
    }

    @PutMapping("/Update")
    public Company updateCompany(@RequestBody Company company) {
        return companyService.updateCompany(company);
    }
}
