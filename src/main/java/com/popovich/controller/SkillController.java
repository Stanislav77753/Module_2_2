package com.popovich.controller;

import com.popovich.model.Skill;
import com.popovich.service.SkillService;

import java.util.List;

public class SkillController {
    private SkillService skillService = new SkillService();

    public List<String> getAllSkills(){
        return skillService.getAllSkills();
    }

    public void addSkill(Skill skill){
        skillService.addSkill(skill);
    }
}
