package com.popovich.repository.repoImp;

import com.popovich.exceptions.EntityNotExistsException;
import com.popovich.model.Company;
import com.popovich.repository.CompanyRepo;
import com.popovich.sqlCommands.SqlGenericCommand;
import com.popovich.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class CompanyRepoImp implements CompanyRepo {
    private static Connection connection = ConnectionUtil.getInstance().getConnection();
    private static final String TABLE = "companies";
    private static final List<String> COLUMNS_NAME = Arrays.asList("company_name");
    private SqlGenericCommand sqlGenericCommand = new SqlGenericCommand();

    @Override
    public void save(Company company) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlGenericCommand.insert(this, company));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Company company) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlGenericCommand.delete(this, company));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Company company) {

    }

    @Override
    public Company getById(Long id) throws EntityNotExistsException {
        List<String> allCompanies = getAll(connection, this);
        for(String company : allCompanies){
            String[] companyString = company.split(",");
            if(new Long(companyString[0]).equals(id)){
                return new Company(new Long(companyString[0]), companyString[1]);
            }
        }
        throw new EntityNotExistsException("Company with this id not exists");
    }

    public List<String> getAllCompanies(){
        return getAll(connection, this);
    }

}
