package com.popovich.repository.repoImp;

import com.popovich.exceptions.EntityNotExistsException;
import com.popovich.model.Developer;
import com.popovich.sqlCommands.SqlGenericCommand;
import com.popovich.util.ConnectionUtil;
import com.popovich.repository.DeveloperRepo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class DeveloperRepoImp implements DeveloperRepo {
    private static Connection connection = ConnectionUtil.getInstance().getConnection();
    private static final String TABLE = "developers";
    private static final List<String> COLUMNS_NAME = Arrays.asList("lname", "name", "salary");
    SqlGenericCommand sqlGenericCommand = new SqlGenericCommand();


    @Override
    public void save(Developer developer) {
        try(Statement devStatement = connection.createStatement()) {
            devStatement.executeUpdate(sqlGenericCommand.insert(this, developer));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Developer developer) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlGenericCommand.delete(this, developer));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Developer developer) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlGenericCommand.update(this, developer));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Developer getById(Long id) throws EntityNotExistsException {
        List<String> allDevelopers = getAll(connection,this);
        for(String developer : allDevelopers){
            String[] devString = developer.split(",");
            if(new Long(devString[0]).equals(id)){
                Developer newDeveloper = new Developer(new Long(devString[0]), devString[2], devString[1],
                        new Integer(devString[3]));
                return newDeveloper;
            }
        }
        throw new EntityNotExistsException("Developer with this id not exists");
    }

    public List<String> gelAllDevelopers(){
        return getAll(connection, this);
    }





}
