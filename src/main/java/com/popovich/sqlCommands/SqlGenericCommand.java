package com.popovich.sqlCommands;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SqlGenericCommand<Repository, Object> {

    public String insert(Repository repository, Object object){
        String[] arrayEntity = getEntity(object).split(";");
        if(!arrayEntity[0].equals("0")){
            return ("INSERT INTO " + getTable(repository) + " VALUES" + getInsertManyToMany(arrayEntity));
        }
       return ("INSERT INTO " + getTable(repository) + " VALUES" + getInsertForEntity(arrayEntity));
    }

    public String delete(Repository repository, Object object){
        List<String> columnsName = getColumnsName(repository);
        String[] arrayEntity = getEntity(object).split(";");
        if(columnsName.size() == 1){
            return "DELETE FROM " + getTable(repository) + getComForOneColumn(columnsName, arrayEntity[0]);
        }
        return "DELETE FROM " +  getTable(repository) + " WHERE " + getComForSeveralColumn(columnsName, arrayEntity);
    }

    public String selectALL(Repository repository){
        return "SELECT * FROM " + getTable(repository);
    }

    public String update(Repository repository, Object object){
        List<String> columnsName = getColumnsName(repository);
        String[] arrayEntity = getEntity(object).split(";");
        return "UPDATE " + getTable(repository) + " SET " + columnsName.get(columnsName.size() - 1) + "= "
                + arrayEntity[columnsName.size()] + " WHERE " +  getComForSeveralColumn(columnsName, arrayEntity);
    }







    private String getComForOneColumn(List<String> columnName, String id){
           return " WHERE " + columnName.get(0) +"='" + id + "'";
    }

    private String getInsertManyToMany(String[] entity){
        StringBuilder command = new StringBuilder();
        String skills = entity[entity.length - 1];
        String[] arraySkill = skills.replace("[", "").replace("]", "").split(",");
        for(String str : arraySkill){
            command.append("(" + entity[0] + "," + str + ")" + ",");
        }
        return command.substring(0, command.length() - 1);
    }

    private String getInsertForEntity(String[] entity){
        StringBuilder command = new StringBuilder();
        int count;
        if(entity.length == 2){
            count = 1;
        }else{
            count = entity.length - 2;
        }
        for(int i = 0; i <= count; i++){
            if(i != entity.length - 1){
                command.append("'" + entity[i] + "'" + ",");
            }else{
                command.append("'" + entity[i] + "'");
            }
        }
        if(command.lastIndexOf(",") == command.length() - 1){
            return "(" + command.substring(0, command.lastIndexOf(",")) + ")";
        }
        return "(" + command + ")";
    }

    private String getComForSeveralColumn(List<String> columnName, String[] entity){
        StringBuilder command = new StringBuilder();
        for(int i = 1; i <= columnName.size() - 1; i++ ){
            if(i != columnName.size() - 1){
                command.append(columnName.get(i - 1) + "='" + entity[i] + "'" + " AND ");
            }else{
                command.append(columnName.get(i - 1) + "='" + entity[i] + "'");
            }
        }
        return command.toString();
    }

    private String getTable(Repository repository) {
        String table = null;
        try {
            Field fieldRepo = repository.getClass().getDeclaredField("TABLE");
            fieldRepo.setAccessible(true);
            table = ((String) fieldRepo.get(repository));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return table;
    }

    private List<String> getColumnsName(Repository repository){
        List<String> columnsName = null;
        try{
            Field fieldRepo = repository.getClass().getDeclaredField("COLUMNS_NAME");
            fieldRepo.setAccessible(true);
            columnsName = ((List) fieldRepo.get(repository));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return columnsName;
    }

    private String getEntity(Object object){
        StringBuilder entity = new StringBuilder();
        List<Field> listAllFields = getAllFields(object);
        try{
            for(int i = listAllFields.size() - 1; i >= 0; i--){
                Field fieldObj = listAllFields.get(i);
                fieldObj.setAccessible(true);
                entity.append(fieldObj.get(object) + ";");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return entity.toString().substring(0, entity.length() - 1);
    }

    private List<Field> getAllFields(Object object){
        List<Field> listFieldObj = new ArrayList<>();
        Class tempClass = object.getClass();
        while(tempClass != null){
            listFieldObj.addAll(Arrays.asList(tempClass .getDeclaredFields()));
            tempClass = tempClass .getSuperclass();
        }
        return listFieldObj;
    }



}
