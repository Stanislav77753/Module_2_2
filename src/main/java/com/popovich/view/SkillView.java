package com.popovich.view;

import com.popovich.controller.SkillController;
import com.popovich.exceptions.EntityAlreadyExistsException;
import com.popovich.model.Skill;

import java.util.List;
import java.util.Scanner;

public class SkillView {
    private SkillController skillController = new SkillController();
    private Scanner scanner = new Scanner(System.in);

    public void addSkill() throws EntityAlreadyExistsException {
        skillController.addSkill(createSkill());
    }

    private Skill createSkill() throws EntityAlreadyExistsException {
        List<String> skills = skillController.getAllSkills();
        Skill newSkill = new Skill(new Long(0),enterName());
        for(String skill: skills){
            String[] skillArr = skill.split(",");
            if(newSkill.getName().equals(skillArr[1])){
                throw new EntityAlreadyExistsException("This skill has already existed in the database");
            }
        }
        return newSkill;
    }

    private String enterName(){
        System.out.println("Enter name of skill");
        return scanner.nextLine().trim();
    }
}
