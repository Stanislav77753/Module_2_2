package com.popovich.model;

public class Skill extends NamedEntity{

    public Skill(Long id, String name) {
        super(id, name);
    }

    public String getAllParametrs(){
        return "(" + this.getId() + ",'"  + this.getName() + "'" + ")";
    }
}
