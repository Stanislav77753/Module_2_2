package com.popovich.repository.repoImp;

import com.popovich.exceptions.EntityNotExistsException;
import com.popovich.model.Skill;
import com.popovich.repository.SkillRepo;
import com.popovich.sqlCommands.SqlGenericCommand;
import com.popovich.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class SkillRepoImp implements SkillRepo {
    private static Connection connection = ConnectionUtil.getInstance().getConnection();
    private static final String TABLE = "skills";
    private static final List<String> COLUMNS_NAME = Arrays.asList("skill_name");
    SqlGenericCommand sqlGenericCommand = new SqlGenericCommand();


    @Override
    public void save(Skill skill) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlGenericCommand.insert(this, skill));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Skill skill) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlGenericCommand.delete(this, skill));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Skill skill) {

    }

    @Override
    public Skill getById(Long id) throws EntityNotExistsException {
        List<String> allSkills = getAll(connection, this);
        for(String skill : allSkills){
            String[] skillString = skill.split(",");
            if(new Long(skillString[0]).equals(id)){
                return new Skill(new Long(skillString[0]), skillString[1]);
            }
        }
        throw new EntityNotExistsException("Developer with this id not exists");
    }

    public List<String> getAllSkills(){
        return getAll(connection, this);
    }

}
