package com.popovich.sqlCommands.sqlComImp;

public enum TableColumns {
    developer_skills("developer_id", "skill_id"),
    project_developers("developer_id", "project_id"),
    company_projects("company_id", "project_id"),
    customer_projects("customer_id", "project_id");

    private String table1;
    private String table2;

    TableColumns(String table1, String table2) {
        this.table1 = table1;
        this.table2 = table2;
    }

    public String getTable1() {
        return table1;
    }

    public String getTable2() {
        return table2;
    }
}
