package com.popovich.service;

import com.popovich.model.Skill;
import com.popovich.repository.repoImp.SkillRepoImp;

import java.util.List;

public class SkillService {
    private SkillRepoImp skillRepoImp = new SkillRepoImp();

    public List<String> getAllSkills(){
        return skillRepoImp.getAllSkills();
    }

    public void addSkill(Skill skill){
        skillRepoImp.save(skill);
    }
}
