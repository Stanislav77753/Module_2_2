package com.popovich.service;

import com.popovich.model.Project;
import com.popovich.repository.repoImp.ProjectDeveloperRepoImp;
import com.popovich.repository.repoImp.ProjectRepoImp;

import java.util.List;

public class ProjectService {
    private ProjectRepoImp projectRepoImp = new ProjectRepoImp();
    private ProjectDeveloperRepoImp projectDeveloperRepoImp = new ProjectDeveloperRepoImp();

    public List<String> getAllProjects(){
        return projectRepoImp.getAllProjects();
    }

    public void addProject(Project project){
        projectRepoImp.save(project);
        if(!project.getDevelopers().isEmpty()){
            project.setId(getCurrentId(project));
            projectDeveloperRepoImp.save(project);
        }

    }

    public void addDevelopersToProject(Project project){
        project.setId(getCurrentId(project));
        projectDeveloperRepoImp.save(project);
        projectRepoImp.update(project);
    }

    private Long getCurrentId(Project project){
        List<String> projects = getAllProjects();
        for(String projectString: projects){
            String[] projStr = projectString.split(",");
            if(project.getName().equals(projStr[1])){
                return new Long(projStr[0]);
            }
        }
        return null;
    }
}
