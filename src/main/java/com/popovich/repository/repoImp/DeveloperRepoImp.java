package com.popovich.repository.repoImp;

import com.popovich.exceptions.EntityNotExistsException;
import com.popovich.model.Developer;
import com.popovich.util.ConnectionUtil;
import com.popovich.repository.DeveloperRepo;
import com.popovich.sqlCommands.SqlCommand;
import com.popovich.sqlCommands.sqlComImp.SqlComDevImp;
import com.popovich.sqlCommands.sqlComImp.SqlComManyToManyImp;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DeveloperRepoImp implements DeveloperRepo {
    private static Connection connection = ConnectionUtil.getInstance().getConnection();
    private String tableDevelopers = "developers";
    private String tableDeveloperSkills = "developer_skills";
    private SqlCommand sqlCommand = new SqlComDevImp();
    private SqlCommand sqlComDevSkill = new SqlComManyToManyImp();


    @Override
    public void save(Developer developer) {
        try(Statement devStatement = connection.createStatement()) {
            devStatement.executeUpdate(sqlCommand.insert(tableDevelopers, new Developer[]{developer}));
            developer.setId(getCurrentId());
            addSkillsForDeveloper(developer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Developer developer) {
        try(Statement statement = connection.createStatement()) {
            deleteSkillsForDeveloper(developer);
            statement.executeUpdate(sqlCommand.delete(tableDevelopers, new Developer[]{developer}));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Developer developer) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand.update(tableDevelopers, new Developer[]{developer}));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Developer getById(Long id) throws EntityNotExistsException {
        List<String> allDevelopers = getAll(connection, sqlCommand, tableDevelopers);
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
        return getAll(connection, sqlCommand, tableDevelopers);
    }

    private Long getCurrentId(){
        List<String> developers = gelAllDevelopers();
        String[] devArr = developers.get(developers.size() - 1).split(",");
        return new Long(devArr[0]);
    }

    public void addSkillsForDeveloper(Developer developer){
        try(Statement developerSkillsStatment = connection.createStatement()) {
            if(!developer.getSkills().isEmpty()){
                for(Long id: developer.getSkills()){
                    developerSkillsStatment.executeUpdate(sqlComDevSkill.insert(tableDeveloperSkills,
                            new Long[]{developer.getId(), id}));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getDeveloperSkills(){
        List<String> developerSkills = getAll(connection, sqlComDevSkill, tableDeveloperSkills);
        return developerSkills;
    }

    public void deleteSkillsForDeveloper(Developer developer){
        try(Statement developerSkillsStatment = connection.createStatement()) {
            if(!developer.getSkills().isEmpty()){
                for(Long id: developer.getSkills()){
                    developerSkillsStatment.executeUpdate(sqlComDevSkill.delete(tableDeveloperSkills,
                            new Long[]{developer.getId(), id}));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
