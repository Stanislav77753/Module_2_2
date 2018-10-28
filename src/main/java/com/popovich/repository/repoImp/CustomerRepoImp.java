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

public class CustomerRepoImp implements CustomerRepo {
    private static Connection connection = ConnectionUtil.getInstance().getConnection();
    private static final String TABLE = "customers";
    private static final List<String> COLUMNS_NAME = Arrays.asList("customer_name");
    SqlGenericCommand sqlGenericCommand = new SqlGenericCommand();

    @Override
    public void save(Customer customer) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlGenericCommand.insert(this, customer));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Customer customer) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlGenericCommand.delete(this, customer));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public Customer getById(Long id) throws EntityNotExistsException {
        List<String> allCustomers = getAll(connection, this);
        for(String customer : allCustomers){
            String[] customerString = customer.split(",");
            if(new Long(customerString[0]).equals(id)){
                return new Customer(new Long(customerString[0]), customerString[1]);
            }
        }
        throw new EntityNotExistsException("Developer with this id not exists");
    }

    public List<String> getAllCustomers(){
        return getAll(connection, this);
    }

}
