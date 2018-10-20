package com.popovich.controller;

import com.popovich.model.Company;
import com.popovich.service.CompanyService;

import java.util.List;

public class CompanyController {
    private CompanyService companyService = new CompanyService();

    public List<String> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    public void addCompany(Company company){
        companyService.addCompany(company);
    }
}
