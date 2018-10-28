package com.popovich.repository.repoImp;

import com.popovich.exceptions.EntityNotExistsException;
import com.popovich.model.Customer;
import com.popovich.repository.CustomerRepo;
import com.popovich.sqlCommands.SqlGenericCommand;
import com.popovich.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class CustomerProjectRepoImp implements CustomerRepo {
    private static Connection connection = ConnectionUtil.getInstance().getConnection();
    private static  final String TABLE = "customer_projects";
    private static  final List<String> COLUMNS_NAME = Arrays.asList("customer_id");
    SqlGenericCommand sqlGenericCommand = new SqlGenericCommand();

    @Override
    public void save(Customer customer) {
        try(Statement customerProjectStatment = connection.createStatement()) {
            customerProjectStatment.executeUpdate(sqlGenericCommand.insert(this, customer));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Customer customer) {
        try(Statement customerProjectStatment = connection.createStatement()) {
            customerProjectStatment.executeUpdate(sqlGenericCommand.delete(this, customer));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public Customer getById(Long aLong) throws EntityNotExistsException {
        return null;
    }
}
