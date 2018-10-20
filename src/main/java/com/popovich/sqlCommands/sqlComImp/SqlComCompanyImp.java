package com.popovich.sqlCommands.sqlComImp;

import com.popovich.model.Company;
import com.popovich.sqlCommands.SqlComCompany;

public class SqlComCompanyImp implements SqlComCompany {
    @Override
    public String insert(String tableName, Company ...company) {
        return "INSERT INTO " + tableName + " VALUES" + company[0].getAllParametrs();
    }

    @Override
    public String delete(String tableName, Company ...company) {
        return "DELETE FROM " + tableName + " WHERE name='" + company[0].getName() + "'";
    }

    @Override
    public String update(String tableName, Company ...company) {
        return null;
    }
}
