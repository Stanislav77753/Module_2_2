package com.popovich.view;

import com.popovich.controller.CompanyController;
import com.popovich.controller.ProjectController;
import com.popovich.exceptions.EntityAlreadyExistsException;
import com.popovich.model.Company;

import java.util.List;
import java.util.Scanner;

public class CompanyView {
    private CompanyController companyController = new CompanyController();
    private ProjectController projectController = new ProjectController();
    private static Scanner scanner = new Scanner(System.in);

    public void addCompany() throws EntityAlreadyExistsException {
        Company newCompany = createCompany();
        if(isCompanyExists(newCompany)){
            throw new EntityAlreadyExistsException("This company has already existed in the database");
        }
        addProjectForCompany(newCompany);
        companyController.addCompany(newCompany);
    }

    private void  addProjectForCompany(Company company){
        do{
            System.out.println("Enter project name for company. For cancel enter \"cancel\"");
            String name = scanner.nextLine().trim();
            if(name.equals("cancel")){
                break;
            }else{
                List<String> projectsFromDatabase = projectController.getAllProjects();
                for(int i = 0; i < projectsFromDatabase.size(); i++){
                    String[] projectString = projectsFromDatabase.get(i).split(",");
                    if(name.equals(projectString[1])){
                        company.addProjects(new Long(projectString[0]));
                        break;
                    }else if(name.equals(projectString[1]) && i == projectsFromDatabase.size() - 1){
                        System.out.println("This project is absent in database. At first add this skill in database");
                    }
                }
            }
        }while(true);
    }

    private boolean isCompanyExists(Company company){
        List<String> companies = companyController.getAllCompanies();
        for(String com: companies){
            String[] companyArr = com.split(",");
            if(company.getName().equals(companyArr[1]) ){
                company.setId(new Long(companyArr[0]));
                return true;
            }
        }
        return false;
    }

    private Company createCompany(){
        return new Company(null, enterName());
    }

    private String enterName(){
        System.out.println("Enter company name");
        return scanner.nextLine().trim();
    }

}
