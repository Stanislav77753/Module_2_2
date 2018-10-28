package com.popovich.service;

import com.popovich.model.Company;
import com.popovich.repository.repoImp.CompanyProjectRepoImp;
import com.popovich.repository.repoImp.CompanyRepoImp;

import java.util.List;

public class CompanyService {
    private CompanyRepoImp companyRepoImp = new CompanyRepoImp();
    private CompanyProjectRepoImp companyProjectRepoImp = new CompanyProjectRepoImp();

    public List<String> getAllCompanies(){
        return companyRepoImp.getAllCompanies();
    }

    public void addCompany(Company company){
        companyRepoImp.save(company);
        if(!company.getProjects().isEmpty()){
            company.setId(getCurrentId());
            companyProjectRepoImp.save(company);
        }
    }

    private Long getCurrentId(){
        List<String> companies = getAllCompanies();
        String[] comArr = companies.get(companies.size() - 1).split(",");
        return new Long(comArr[0]);
    }
}
