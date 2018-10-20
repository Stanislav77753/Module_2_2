package com.popovich.repository.repoImp;

import com.popovich.exceptions.EntityNotExistsException;
import com.popovich.model.Project;
import com.popovich.repository.ProjectRepo;
import com.popovich.util.ConnectionUtil;
import com.popovich.sqlCommands.SqlCommand;
import com.popovich.sqlCommands.sqlComImp.SqlComManyToManyImp;
import com.popovich.sqlCommands.sqlComImp.SqlComProjectImp;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ProjectRepoImp implements ProjectRepo {
    private static Connection connection = ConnectionUtil.getInstance().getConnection();
    private String table = "projects";
    private String tableProjectDeveloper = "project_developers";
    private SqlCommand sqlCommand = new SqlComProjectImp();
    private SqlCommand sqlComProjectDevelopers = new SqlComManyToManyImp();

    @Override
    public void save(Project project) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand.insert(table, new Project[]{project}));
            project.setId(getCurrentId());
            addDevelopersToProject(project);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Project project) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand.delete(table, new Project[]{project}));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Project project) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand.update(table, new Project[]{project}));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Project getById(Long id) throws EntityNotExistsException {
        List<String> allProjects = getAll(connection, sqlCommand, table);
        for(String project : allProjects){
            String[] projectString = project.split(",");
            if(new Long(projectString[0]).equals(id)){
                return new Project(new Long(projectString[0]), projectString[1], new Integer(projectString[2]));
            }
        }
        throw new EntityNotExistsException("Developer with this id not exists");
    }

    public List<String> getAllProjects(){
        return getAll(connection, sqlCommand, table);
    }

    public List<String> getProjectDevelopers(){
        List<String> projectDevelopers = getAll(connection, sqlComProjectDevelopers, tableProjectDeveloper);
        return projectDevelopers;
    }

    private Long getCurrentId(){
        List<String> projects = getAllProjects();
        String[] projectArr = projects.get(projects.size() - 1).split(",");
        return new Long(projectArr[0]);
    }

    public void addDevelopersToProject(Project project){
        try(Statement projectDeveloperStatment = connection.createStatement()) {
            if(!project.getDevelopers().isEmpty()){
                for(Long id: project.getDevelopers()){
                    projectDeveloperStatment.executeUpdate(sqlComProjectDevelopers.insert(tableProjectDeveloper,
                            new Long[]{id, project.getId()}));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
