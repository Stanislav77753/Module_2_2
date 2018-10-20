package com.popovich.repository.repoImp;

import com.popovich.exceptions.EntityNotExistsException;
import com.popovich.model.Customer;
import com.popovich.repository.CustomerRepo;
import com.popovich.util.ConnectionUtil;
import com.popovich.sqlCommands.SqlCommand;
import com.popovich.sqlCommands.sqlComImp.SqlComCustomerImp;
import com.popovich.sqlCommands.sqlComImp.SqlComManyToManyImp;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CustomerRepoImp implements CustomerRepo {
    private static Connection connection = ConnectionUtil.getInstance().getConnection();
    private String table = "customers";
    private String customerProjectTable = "customer_projects";
    private SqlCommand sqlCommand = new SqlComCustomerImp();
    private SqlCommand sqlComCustomerProjects = new SqlComManyToManyImp();

    @Override
    public void save(Customer customer) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand.insert(table, new Customer[]{customer}));
            customer.setId(getCurrentId());
            addProjectToCustomer(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Customer customer) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand.delete(table, customer));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public Customer getById(Long id) throws EntityNotExistsException {
        List<String> allCustomers = getAll(connection, sqlCommand, table);
        for(String customer : allCustomers){
            String[] customerString = customer.split(",");
            if(new Long(customerString[0]).equals(id)){
                return new Customer(new Long(customerString[0]), customerString[1]);
            }
        }
        throw new EntityNotExistsException("Developer with this id not exists");
    }

    public List<String> getAllCustomers(){
        return getAll(connection, sqlCommand, table);
    }

    private Long getCurrentId(){
        List<String> customers = getAllCustomers();
        String[] cusArr = customers.get(customers.size() - 1).split(",");
        return new Long(cusArr[0]);
    }

    public void addProjectToCustomer(Customer customer){
        try(Statement companyProjectsStatment = connection.createStatement()) {
            if(!customer.getProjects().isEmpty()){
                for(Long id: customer.getProjects()){
                    companyProjectsStatment.executeUpdate(sqlComCustomerProjects.insert(customerProjectTable,
                            new Long[]{customer.getId(), id}));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
