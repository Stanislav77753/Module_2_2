package com.popovich.view;

import com.popovich.controller.CustomerController;
import com.popovich.controller.ProjectController;
import com.popovich.exceptions.EntityAlreadyExistsException;
import com.popovich.model.Customer;

import java.util.List;
import java.util.Scanner;

public class CustomerView {
    private CustomerController customerController = new CustomerController();
    private ProjectController projectController = new ProjectController();
    private static Scanner scanner = new Scanner(System.in);

    public void addCompany() throws EntityAlreadyExistsException {
        Customer newCustomer = createCustomer();
        if(isCustomerExists(newCustomer)){
            throw new EntityAlreadyExistsException("This company has already existed in the database");
        }
        addProjectForCustomer(newCustomer);
        customerController.addCustomer(newCustomer);
    }

    private void  addProjectForCustomer(Customer customer){
        do{
            System.out.println("Enter project name for customer. For cancel enter \"cancel\"");
            String name = scanner.nextLine().trim();
            if(name.equals("cancel")){
                break;
            }else{
                List<String> projectsFromDatabase = projectController.getAllProjects();
                for(int i = 0; i < projectsFromDatabase.size(); i++){
                    String[] projectString = projectsFromDatabase.get(i).split(",");
                    if(name.equals(projectString[1])){
                        customer.addProjects(new Long(projectString[0]));
                        break;
                    }else if(name.equals(projectString[1]) && i == projectsFromDatabase.size() - 1){
                        System.out.println("This project is absent in database. At first add this skill in database");
                    }
                }
            }
        }while(true);
    }

    private boolean isCustomerExists(Customer customer){
        List<String> customers = customerController.getAllCustomers();
        for(String cus: customers){
            String[] customerArr = cus.split(",");
            if(customer.getName().equals(customerArr[1]) ){
                customer.setId(new Long(customerArr[0]));
                return true;
            }
        }
        return false;
    }

    private Customer createCustomer(){
        return new Customer(new Long(0), enterName());
    }

    private String enterName(){
        System.out.println("Enter customer name");
        return scanner.nextLine().trim();
    }
}
