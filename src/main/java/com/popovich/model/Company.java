package com.popovich.model;

import java.util.ArrayList;
import java.util.List;

public class Company extends NamedEntity {
    private List<Long> projects = new ArrayList<>();
    public Company(Long id, String name) {
        super(id, name);
    }

    public String getAllParametrs(){
        return "(" + this.getId() + ",'"  + this.getName() + "'" + ")";
    }

    public List<Long> getProjects() {
        return projects;
    }

    public void addProjects(Long id) {
        this.projects.add(id);
    }
}
