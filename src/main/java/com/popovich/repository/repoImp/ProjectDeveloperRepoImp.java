package com.popovich.repository.repoImp;

import com.popovich.exceptions.EntityNotExistsException;
import com.popovich.model.Project;
import com.popovich.repository.ProjectRepo;
import com.popovich.sqlCommands.SqlGenericCommand;
import com.popovich.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class ProjectDeveloperRepoImp implements ProjectRepo {
    private static Connection connection = ConnectionUtil.getInstance().getConnection();
    private static  final String TABLE = "project_developers";
    private static  final List<String> COLUMNS_NAME = Arrays.asList("project_id");
    SqlGenericCommand sqlGenericCommand = new SqlGenericCommand();

    @Override
    public void save(Project project) {
        try(Statement developerSkillsStatment = connection.createStatement()) {
            developerSkillsStatment.executeUpdate(sqlGenericCommand.insert(this, project));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Project project) {
        try(Statement developerSkillsStatment = connection.createStatement()) {
            developerSkillsStatment.executeUpdate(sqlGenericCommand.delete(this, project));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Project project) {

    }

    @Override
    public Project getById(Long aLong) throws EntityNotExistsException {
        return null;
    }
}
