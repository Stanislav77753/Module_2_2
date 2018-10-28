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

public class CompanyProjectRepoImp implements CompanyRepo {
    private static Connection connection = ConnectionUtil.getInstance().getConnection();
    private static  final String TABLE = "company_projects";
    private static  final List<String> COLUMNS_NAME = Arrays.asList("company_id");
    SqlGenericCommand sqlGenericCommand = new SqlGenericCommand();

    @Override
    public void save(Company company) {
        try(Statement companyProjectStatment = connection.createStatement()) {
            companyProjectStatment.executeUpdate(sqlGenericCommand.insert(this, company));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Company company) {
        try(Statement companyProjectStatment = connection.createStatement()) {
            companyProjectStatment.executeUpdate(sqlGenericCommand.delete(this, company));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Company company) {

    }

    @Override
    public Company getById(Long aLong) throws EntityNotExistsException {
        return null;
    }

}
