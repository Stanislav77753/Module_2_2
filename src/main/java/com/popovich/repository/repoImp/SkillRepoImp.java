package com.popovich.repository.repoImp;

import com.popovich.exceptions.EntityNotExistsException;
import com.popovich.model.Skill;
import com.popovich.repository.SkillRepo;
import com.popovich.util.ConnectionUtil;
import com.popovich.sqlCommands.SqlCommand;
import com.popovich.sqlCommands.sqlComImp.SqlComSkillImp;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SkillRepoImp implements SkillRepo {
    private static Connection connection = ConnectionUtil.getInstance().getConnection();
    private String table = "skills";
    private SqlCommand sqlCommand = new SqlComSkillImp();


    @Override
    public void save(Skill skill) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand.insert(table, new Skill[]{skill}));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Skill skill) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand.delete(table, skill));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Skill skill) {

    }

    @Override
    public Skill getById(Long id) throws EntityNotExistsException {
        List<String> allSkills = getAll(connection, sqlCommand, table);
        for(String skill : allSkills){
            String[] skillString = skill.split(",");
            if(new Long(skillString[0]).equals(id)){
                return new Skill(new Long(skillString[0]), skillString[1]);
            }
        }
        throw new EntityNotExistsException("Developer with this id not exists");
    }

    public List<String> getAllSkills(){
        return getAll(connection, sqlCommand, table);
    }

}
