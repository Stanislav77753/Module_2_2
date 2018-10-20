package com.popovich.sqlCommands.sqlComImp;

import com.popovich.model.Customer;
import com.popovich.sqlCommands.SqlComCustomer;

public class SqlComCustomerImp implements SqlComCustomer {
    @Override
    public String insert(String tableName, Customer ...customer) {
        return "INSERT INTO " + tableName + " VALUES" + customer[0].getAllParametrs();
    }

    @Override
    public String delete(String tableName, Customer ...customer) {
        return "DELETE FROM " + tableName + " WHERE name='" + customer[0].getName() + "'";
    }

    @Override
    public String update(String tableName, Customer ...customer) {
        return null;
    }
}
