package com.popovich.sqlCommands.sqlComImp;

import com.popovich.model.Developer;
import com.popovich.sqlCommands.SqlComDev;

public class SqlComDevImp implements SqlComDev {

    @Override
    public String insert(String tableName, Developer ...developer) {
        return "INSERT INTO " + tableName + " VALUES" + developer[0].getAllParametrs();
    }

    @Override
    public String delete(String tableName, Developer ...developer) {
        return "DELETE FROM " + tableName + " WHERE lname='" + developer[0].getlName() + "' AND " + "name='"
                + developer[0].getName() +"'";
    }

    @Override
    public String update(String tableName, Developer ...developer) {
        return "UPDATE " + tableName + " SET salary=" + developer[0].getSalary() + " WHERE lname='"
                + developer[0].getlName() + "' AND " + "name='" + developer[0].getName() +"'";
    }



}
