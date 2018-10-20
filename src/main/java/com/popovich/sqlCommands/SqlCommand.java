package com.popovich.sqlCommands;

public interface SqlCommand<E> {

    String insert(String tableName,E ...e);
    String delete(String tableName, E ...e);
    String update(String tableName, E ...e);
    default String selectAll(String tableName){
        return "SELECT * FROM " + tableName;
    }
}
