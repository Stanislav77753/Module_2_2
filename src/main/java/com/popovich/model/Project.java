package com.popovich.model;

import java.util.ArrayList;
import java.util.List;

public class Project extends NamedEntity {
    private List<Long> developers = new ArrayList<>();
    private Integer cost = 0;

    public Project(Long id, String name) {
        super(id, name);
    }

    public Project(Long id, String name, Integer cost) {
        super(id, name);
        this.cost = cost;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getAllParametrs(){
        return "(" + this.getId() + ",'"  + this.getName() + "'," + this.cost + ")";
    }

    public List<Long> getDevelopers() {
        return developers;
    }
    public void addDevelopers(Long developerId) {
        this.developers.add(developerId);
    }

    public void increaseCost(Integer developerSalary){
        this.cost += developerSalary;
    }
}
