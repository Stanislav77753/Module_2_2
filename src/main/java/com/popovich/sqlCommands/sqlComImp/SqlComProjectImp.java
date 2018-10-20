package com.popovich.sqlCommands.sqlComImp;

import com.popovich.model.Project;
import com.popovich.sqlCommands.SqlComProject;

public class SqlComProjectImp implements SqlComProject {
    @Override
    public String insert(String tableName, Project ...project) {
        return "INSERT INTO " + tableName + " VALUES" + project[0].getAllParametrs();
    }

    @Override
    public String delete(String tableName, Project ...project) {
        return "DELETE FROM " + tableName + " WHERE name='" + project[0].getName() + "'";
    }

    @Override
    public String update(String tableName, Project ...project) {
        return "UPDATE " + tableName + " SET cost=" + project[0].getCost() + " WHERE name='"
                + project[0].getName() +"'";
    }
}
