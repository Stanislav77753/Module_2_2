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

public class ProjectRepoImp implements ProjectRepo {
    private static Connection connection = ConnectionUtil.getInstance().getConnection();
    private static final String TABLE = "projects";
    private static final List<String> COLUMNS_NAME = Arrays.asList("project_name", "cost");
    SqlGenericCommand sqlGenericCommand = new SqlGenericCommand();

    @Override
    public void save(Project project) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlGenericCommand.insert(this, project));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Project project) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlGenericCommand.delete(this, project));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Project project) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlGenericCommand.update(this, project));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Project getById(Long id) throws EntityNotExistsException {
        List<String> allProjects = getAll(connection,this);
        for(String project : allProjects){
            String[] projectString = project.split(",");
            if(new Long(projectString[0]).equals(id)){
                return new Project(new Long(projectString[0]), projectString[1], new Integer(projectString[2]));
            }
        }
        throw new EntityNotExistsException("Developer with this id not exists");
    }

    public List<String> getAllProjects(){
        return getAll(connection, this);
    }


}
