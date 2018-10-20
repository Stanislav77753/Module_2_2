package com.popovich.controller;

import com.popovich.model.Developer;
import com.popovich.service.DeveloperService;

import java.util.List;

public class DeveloperController {
    private DeveloperService developerService = new DeveloperService();

    public void addDeveloper(Developer developer){
        developerService.addDeveloper(developer);
    }

    public List<String> getAllDevelopers(){
        return developerService.getAllDevelopers();
    }

    public void addSkillsForDeveloper(Developer developer){
        developerService.addSkillsForDeveloper(developer);
    }

    public void changeDeveloperSalary(Developer developer){
        developerService.changeDeveloperSalary(developer);
    }

    public void deleteDeveloper(Developer developer){
        developerService.deleteDeveloper(developer);
    }
}
