package com.popovich.service;

import com.popovich.model.Company;
import com.popovich.repository.repoImp.CompanyRepoImp;

import java.util.List;

public class CompanyService {
    private CompanyRepoImp companyRepoImp = new CompanyRepoImp();

    public List<String> getAllCompanies(){
        return companyRepoImp.getAllCompanies();
    }

    public void addCompany(Company company){
        companyRepoImp.save(company);
    }
}
