package com.popovich.service;

import com.popovich.model.Developer;
import com.popovich.repository.repoImp.DeveloperRepoImp;
import com.popovich.repository.repoImp.DeveloperSkillRepoImp;

import java.util.List;

public class DeveloperService {
    private DeveloperRepoImp developerRepoImp = new DeveloperRepoImp();
    private DeveloperSkillRepoImp developerSkillRepoImp = new DeveloperSkillRepoImp();

    public void addDeveloper(Developer developer){
        developerRepoImp.save(developer);
        if(!developer.getSkills().isEmpty()){
            developer.setId(getCurrentId(developer));
            developerSkillRepoImp.save(developer);
        }
    }

    public List<String> getAllDevelopers(){
        return developerRepoImp.gelAllDevelopers();
    }


    public void changeDeveloperSalary(Developer developer){
        developerRepoImp.update(developer);
    }

    public void deleteDeveloper(Developer developer){
        List<String> developerSkills = developerSkillRepoImp.getDeveloperSkills();
        for(String skills: developerSkills){
            String[] developer_skills = skills.split(",");
            if(developer.getId().equals(new Long(developer_skills[0]))){
                developer.addSkills(new Long(developer_skills[1]));
            }
        }
        if(!developer.getSkills().isEmpty()){
            developerSkillRepoImp.delete(developer);
        }
        developerRepoImp.delete(developer);
    }

    private Long getCurrentId(Developer developer){
        List<String> developers = developerRepoImp.gelAllDevelopers();
        for(String developerString: developers){
            String[] devStr = developerString.split(",");
            if(developer.getlName().equals(devStr[2]) && developer.getName().equals(devStr[1])){
                return new Long(devStr[0]);
            }
        }
        return null;
    }

    public void addSkillToDeveloper(Developer developer){
        developer.setId(getCurrentId(developer));
        developerSkillRepoImp.save(developer);
    }

}
