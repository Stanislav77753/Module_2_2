package com.popovich.repository.repoImp;

import com.popovich.exceptions.EntityNotExistsException;
import com.popovich.model.Company;
import com.popovich.repository.CompanyRepo;
import com.popovich.util.ConnectionUtil;
import com.popovich.sqlCommands.SqlCommand;
import com.popovich.sqlCommands.sqlComImp.SqlComCompanyImp;
import com.popovich.sqlCommands.sqlComImp.SqlComManyToManyImp;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CompanyRepoImp implements CompanyRepo {
    private static Connection connection = ConnectionUtil.getInstance().getConnection();
    private String table = "companies";
    private String companyProjectTable = "company_projects";
    private SqlCommand sqlCommand = new SqlComCompanyImp();
    private SqlCommand sqlComCompanyProjects = new SqlComManyToManyImp();

    @Override
    public void save(Company company) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand.insert(table, new Company[]{company}));
            company.setId(getCurrentId());
            addProjectToCompany(company);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Company company) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand.delete(table, company));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Company company) {

    }

    @Override
    public Company getById(Long id) throws EntityNotExistsException {
        List<String> allCompanies = getAll(connection, sqlCommand, table);
        for(String company : allCompanies){
            String[] companyString = company.split(",");
            if(new Long(companyString[0]).equals(id)){
                return new Company(new Long(companyString[0]), companyString[1]);
            }
        }
        throw new EntityNotExistsException("Developer with this id not exists");
    }

    public List<String> getAllCompanies(){
        return getAll(connection, sqlCommand, table);
    }

    private Long getCurrentId(){
        List<String> companies = getAllCompanies();
        String[] comArr = companies.get(companies.size() - 1).split(",");
        return new Long(comArr[0]);
    }
    public void addProjectToCompany(Company company){
        try(Statement companyProjectsStatment = connection.createStatement()) {
            if(!company.getProjects().isEmpty()){
                for(Long id: company.getProjects()){
                    companyProjectsStatment.executeUpdate(sqlComCompanyProjects.insert(companyProjectTable,
                            new Long[]{company.getId(), id}));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
