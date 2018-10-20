package com.popovich.sqlCommands.sqlComImp;

import com.popovich.model.Skill;
import com.popovich.sqlCommands.SqlComSkill;

public class SqlComSkillImp implements SqlComSkill {

    @Override
    public String insert(String tableName, Skill ...skill) {
        return "INSERT INTO " + tableName + " VALUES" + skill[0].getAllParametrs();
    }

    @Override
    public String delete(String tableName, Skill ...skill) {
        return "DELETE FROM " + tableName + " WHERE name='" + skill[0].getName() + "'";
    }

    @Override
    public String update(String tableName, Skill ...skill) {
        return null;
    }

}
