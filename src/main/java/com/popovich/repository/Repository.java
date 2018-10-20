package com.popovich.repository;

import com.popovich.exceptions.EntityNotExistsException;
import com.popovich.sqlCommands.SqlCommand;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public interface Repository<T, ID> {

    void save(T t);
    void delete(T t);
    void update(T t);
    T getById(ID id) throws EntityNotExistsException;
    default List<String> getAll(Connection connection, SqlCommand sqlCommand, String table){
        List<String> allEntyties = new ArrayList<>();
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCommand.selectAll(table))) {
            while(resultSet.next()){
                StringBuilder str = new StringBuilder();
                for(int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++){
                    if(i < (resultSet.getMetaData().getColumnCount())){
                        str.append(resultSet.getString(i) + ",");
                    }
                    else{
                        str.append(resultSet.getString(i));
                    }
                }
                allEntyties.add(str.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allEntyties;
    }


}
