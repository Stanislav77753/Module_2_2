package com.popovich.view;

import com.popovich.controller.DeveloperController;
import com.popovich.controller.ProjectController;
import com.popovich.exceptions.EntityAlreadyExistsException;
import com.popovich.exceptions.EntityNotExistsException;
import com.popovich.model.Project;

import java.util.List;
import java.util.Scanner;

public class ProjectView {
    private ProjectController projectController = new ProjectController();
    private DeveloperController developerController = new DeveloperController();
    private Scanner scanner = new Scanner(System.in);

    public void addProject() throws EntityAlreadyExistsException {

        Project newProject = createProject();
        if(isProjectExists(newProject)){
            throw new EntityAlreadyExistsException("This project has already existed in the database");
        }
        addDevelopersForProject(newProject);
        if(!newProject.getDevelopers().isEmpty()){
            setProjectCost(newProject);
        }
        projectController.addProject(newProject);
    }

    public void addDevelopersToProject() throws EntityNotExistsException {
        Project newProject = createProject();
        if(!isProjectExists(newProject)){
            throw new EntityNotExistsException("This project hasn't existed in database yet.");
        }
        addDevelopersForProject(newProject);
        setProjectCost(newProject);
        projectController.addDevelopersToProject(newProject);
    }

    private void addDevelopersForProject(Project project){
        do{
            System.out.println("Enter developer last name for project. For cancel enter \"cancel\"");
            String lName = scanner.nextLine().trim();
            System.out.println("Enter developer name. For cancel enter \"cancel\"");
            String name = scanner.nextLine().trim();
            if(lName.equals("cancel") || name.equals("cancel")){
                break;
            }else{
                List<String> developersFromDatabase = developerController.getAllDevelopers();
                for(int i = 0; i < developersFromDatabase.size(); i++){
                    String[] developerString = developersFromDatabase.get(i).split(",");
                    if(lName.equals(developerString[1]) && name.equals(developerString[2])){
                        project.addDevelopers(new Long(developerString[0]));
                        break;
                    }else if(!lName.equals(developerString[1]) && !name.equals(developerString[2])
                            && i == developersFromDatabase.size() - 1){
                        System.out.println("This developer is absent in database. At first add this skill in database");
                    }
                }
            }
        }while(true);
    }


    private boolean isProjectExists(Project project){
        List<String> projects = projectController.getAllProjects();
        for(String projStr: projects){
            String[] projectArr = projStr.split(",");
            if(project.getName().equals(projectArr[1])){
                project.setId(new Long(projectArr[0]));
                return true;
            }
        }
        return false;
    }

    private Project createProject(){
        return new Project(null, enterName());
    }

    private String enterName(){
        System.out.println("Enter name of project");
        return scanner.nextLine().trim();
    }
    private void setProjectCost(Project project){
        List<String> developersFromDatabase = developerController.getAllDevelopers();
        List<Long> developersProject = project.getDevelopers();
        for(int i = 0; i < developersFromDatabase.size(); i++){
            String[] developerString = developersFromDatabase.get(i).split(",");
            for(Long id : developersProject){
                if(new Long(developerString[0]).equals(id)){
                    project.increaseCost(new Integer(developerString[3]));
                }
            }
        }
    }


}
