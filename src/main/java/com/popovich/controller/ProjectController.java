package com.popovich.controller;

import com.popovich.model.Project;
import com.popovich.service.ProjectService;

import java.util.List;

public class ProjectController {
    private ProjectService projectService = new ProjectService();

    public List<String> getAllProjects(){
        return projectService.getAllProjects();
    }

    public void addProject(Project project){
        projectService.addProject(project);
    }

    public void addDevelopersToProject(Project project){
        projectService.addDevelopersToProject(project);
    }
}
