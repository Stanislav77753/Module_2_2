package com.popovich.sqlCommands.sqlComImp;

import com.popovich.sqlCommands.SqlCommand;

public class SqlComManyToManyImp implements SqlCommand<Long> {


    @Override
    public String insert(String tableName, Long... e) {
        return "INSERT INTO " + tableName + " VALUES" + "(" + e[0] + "," + e[1] + ")";
    }

    @Override
    public String delete(String tableName, Long... e) {
        if(tableName.equals("developer_skills")){
            return "DELETE FROM " + tableName + " WHERE " + TableColumns.developer_skills.getTable1() + "=" + e[0]
                    + " AND " + TableColumns.developer_skills.getTable2() + "=" + e[1];
        }else if(tableName.equals("project_developers")){
            return "DELETE FROM " + tableName + " WHERE " + TableColumns.project_developers.getTable1() + "=" + e[0]
                    + " AND " + TableColumns.project_developers.getTable2() + "=" + e[1];
        }else if(tableName.equals("company_projects")){
            return "DELETE FROM " + tableName + " WHERE " + TableColumns.company_projects.getTable1() + "=" + e[0]
                    + " AND " + TableColumns.company_projects.getTable2() + "=" + e[1];
        }
        else if(tableName.equals("customer_projects")){
            return "DELETE FROM " + tableName + " WHERE " + TableColumns.customer_projects.getTable1() + "=" + e[0]
                    + " AND " + TableColumns.customer_projects.getTable2() + "=" + e[1];
        }
        else{
            return null;
        }
    }

    @Override
    public String update(String tableName, Long... e) {
        return null;
    }
}
