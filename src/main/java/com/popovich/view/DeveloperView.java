package com.popovich.view;

import com.popovich.controller.DeveloperController;
import com.popovich.controller.SkillController;
import com.popovich.exceptions.EntityAlreadyExistsException;
import com.popovich.exceptions.EntityNotExistsException;
import com.popovich.model.Developer;

import java.util.List;
import java.util.Scanner;

public class DeveloperView {
    private static Scanner scanner = new Scanner(System.in);
    private DeveloperController developerController = new DeveloperController();
    private SkillController skillController = new SkillController();

    public void addDeveloper() throws EntityAlreadyExistsException {
        Developer newDeveloper = createDeveloper();
        if(isDeveloperExists(newDeveloper)){
            throw new EntityAlreadyExistsException("This developer has already existed in the database");
        }
        addDeveloperSkills(newDeveloper);
        addSalary(newDeveloper);
        developerController.addDeveloper(newDeveloper);
    }

    public void addSkillsForDeveloper() throws EntityNotExistsException {
        Developer newDeveloper = createDeveloper();
        if(!isDeveloperExists(newDeveloper)){
            throw new EntityNotExistsException("This developer hasn't existed in database yet.");
        }
        addDeveloperSkills(newDeveloper);
        developerController.addSkillsForDeveloper(newDeveloper);
    }

    public void changeDeveloperSalary() throws EntityNotExistsException {
        Developer newDeveloper = createDeveloper();
        if(!isDeveloperExists(newDeveloper)){
            throw new EntityNotExistsException("This developer hasn't existed in database yet.");
        }
        addSalary(newDeveloper);
        developerController.changeDeveloperSalary(newDeveloper);
    }

    public void deleteDeveloper() throws EntityNotExistsException {
        Developer developer = createDeveloper();
        if(!isDeveloperExists(developer)){
            throw new EntityNotExistsException("This developer hasn't existed in database yet.");
        }
        developerController.deleteDeveloper(developer);
    }

    public void lookAllDevelopers(){
        List<String> developers = developerController.getAllDevelopers();
        for(String dev: developers){
            String[] developer = dev.split(",");
            System.out.println(developer[1] + " " + developer[2]);
        }
    }

    private  Developer createDeveloper(){
        return new Developer(null, enterName("developer_name"),
                enterName("developer_lname"));
    }

    private String enterName(String entity){
        if(entity.equals("developer_name")){
            System.out.println("Enter developer name");
        }else if(entity.equals("developer_lname")){
            System.out.println("Enter developer last name");
        }else if(entity.equals("developer_skill")){
            System.out.println("Enter skill of this developer");
        }
        return readEntity();
    }

    private String readEntity(){
        return scanner.nextLine().trim();
    }

    private boolean isDeveloperExists(Developer developer) {
        List<String> developers = developerController.getAllDevelopers();
        for(String dev: developers){
            String[] devArr = dev.split(",");
            if(developer.getlName().equals(devArr[1]) && developer.getName().equals(devArr[2])){
                developer.setId(new Long(devArr[0]));
                return true;
            }
        }
        return false;
    }

    private void addDeveloperSkills(Developer developer){
        do{
            System.out.println("Enter developer skill. For cancel enter \"cancel\"");
            String command = readEntity();
            if(command.equals("cancel")){
                break;
            }else{
                if(!isSkillExists(command, developer)){
                    System.out.println("This skill is absent in database. At first add this skill in database ");
                }
            }
        }while(true);
    }

    private boolean isSkillExists(String skill, Developer developer){
        List<String> skillFromDataBase = skillController.getAllSkills();
        for(int i = 0; i < skillFromDataBase.size(); i++){
            String[] skillString = skillFromDataBase.get(i).split(",");
            if(skill.equals(skillString[1])){
                developer.addSkills(new Long(skillString[0]));
                return true;
            }else if(!skill.equals(skillString[1]) && i == skillFromDataBase.size() - 1){
                return false;

            }
        }
        return false;
    }

    private void addSalary(Developer developer){
        System.out.println("Enter developer salary.");
        developer.setSalary(new Integer(readEntity()));
    }


}
