package com.popovich.service;

import com.popovich.model.Project;
import com.popovich.repository.repoImp.ProjectRepoImp;

import java.util.List;

public class ProjectService {
    private ProjectRepoImp projectRepoImp = new ProjectRepoImp();

    public List<String> getAllProjects(){
        return projectRepoImp.getAllProjects();
    }

    public void addProject(Project project){
        projectRepoImp.save(project);
    }

    public void addDevelopersToProject(Project project){
        projectRepoImp.addDevelopersToProject(project);
        projectRepoImp.update(project);
    }
}
