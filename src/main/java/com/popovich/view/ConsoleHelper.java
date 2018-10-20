package com.popovich.view;

import com.popovich.exceptions.EntityAlreadyExistsException;
import com.popovich.exceptions.EntityNotExistsException;

import java.util.Scanner;

public class ConsoleHelper {
    private Scanner scanner = new Scanner(System.in);
    private DeveloperView developerView = new DeveloperView();
    private SkillView skillView = new SkillView();
    private ProjectView projectView = new ProjectView();
    private CompanyView companyView = new CompanyView();
    private CustomerView customerView = new CustomerView();

    public void start() {
        do{
            System.out.println("Enter your command");
            String command = scanner.nextLine().toLowerCase().trim();
            try{
                if(command.equals("cancel")){
                    break;
                }else if(command.equals("add developer")){
                    developerView.addDeveloper();
                }else if(command.equals("add skills for developer")){
                    developerView.addSkillsForDeveloper();
                }else if(command.equals("change developer salary")){
                    developerView.changeDeveloperSalary();
                }else if(command.equals("delete developer")){
                    developerView.deleteDeveloper();
                }else if(command.equals("look all developers")){
                    developerView.lookAllDevelopers();
                }else if(command.equals("add skill")){
                    skillView.addSkill();
                }else if(command.equals("add project")){
                    projectView.addProject();
                }else if(command.equals("add developers to project")){
                    projectView.addDevelopersToProject();
                }else if(command.equals("add company")){
                    companyView.addCompany();
                }else if(command.equals("add customer")){
                    customerView.addCompany();
                }
            }catch (EntityAlreadyExistsException | EntityNotExistsException e){
                System.out.println(e.getMessage());
            }
        }while(true);
    }

}
