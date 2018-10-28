package com.popovich.repository.repoImp;

import com.popovich.exceptions.EntityNotExistsException;
import com.popovich.model.Developer;
import com.popovich.repository.DeveloperRepo;
import com.popovich.sqlCommands.SqlGenericCommand;
import com.popovich.util.ConnectionUtil;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class DeveloperSkillRepoImp implements DeveloperRepo {
    private static Connection connection = ConnectionUtil.getInstance().getConnection();
    private static  final String TABLE = "developer_skills";
    private static  final List<String> COLUMNS_NAME = Arrays.asList("developer_id");
    SqlGenericCommand sqlGenericCommand = new SqlGenericCommand();

    @Override
    public void save(Developer developer) {
        try(Statement developerSkillsStatment = connection.createStatement()) {
            developerSkillsStatment.executeUpdate(sqlGenericCommand.insert(this, developer));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Developer developer) {
        try(Statement developerSkillsStatment = connection.createStatement()) {
            developerSkillsStatment.executeUpdate(sqlGenericCommand.delete(this, developer));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Developer developer) {

    }

    @Override
    public Developer getById(Long aLong) throws EntityNotExistsException {
        return null;
    }

    public List<String> getDeveloperSkills(){
        List<String> developerSkills = getAll(connection, this);
        return developerSkills;
    }
}
