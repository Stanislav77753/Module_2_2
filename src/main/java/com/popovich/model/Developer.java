package com.popovich.model;

import java.util.ArrayList;
import java.util.List;

public class Developer extends NamedEntity {
    private String lName;
    private Integer salary = 0;
    private List<Long> skills = new ArrayList<>();

    public Developer(Long id, String name, String lName) {
        super(id, name);
        this.lName = lName;
    }

    public Developer(Long id, String name, String lName, Integer salary) {
        super(id, name);
        this.lName = lName;
        this.salary = salary;
    }

    public List<Long> getSkills() {
        return skills;
    }

    public void addSkills(Long skillId) {
        this.skills.add(skillId);
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getAllParametrs(){
        return "(" + this.getId() + ",'" + this.getlName() + "','" + this.getName() + "'," + this.getSalary() + ")";
    }

}
