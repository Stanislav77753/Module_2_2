package com.popovich.service;

import com.popovich.model.Developer;
import com.popovich.repository.repoImp.DeveloperRepoImp;

import java.util.List;

public class DeveloperService {
    private DeveloperRepoImp developerRepoImp = new DeveloperRepoImp();

    public void addDeveloper(Developer developer){
        developerRepoImp.save(developer);
    }

    public List<String> getAllDevelopers(){
        return developerRepoImp.gelAllDevelopers();
    }

    public void addSkillsForDeveloper(Developer developer){
        developerRepoImp.addSkillsForDeveloper(developer);
    }

    public void changeDeveloperSalary(Developer developer){
        developerRepoImp.update(developer);
    }

    public void deleteDeveloper(Developer developer){
        List<String> developerSkills = developerRepoImp.getDeveloperSkills();
        for(String skills: developerSkills){
            String[] developer_skills = skills.split(",");
            if(developer.getId().equals(new Long(developer_skills[0]))){
                developer.addSkills(new Long(developer_skills[1]));
            }
        }
        developerRepoImp.delete(developer);
    }

}
