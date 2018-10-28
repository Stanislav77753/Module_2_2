package com.popovich.service;

import com.popovich.model.Customer;
import com.popovich.repository.repoImp.CustomerProjectRepoImp;
import com.popovich.repository.repoImp.CustomerRepoImp;

import java.util.List;

public class CustomerService {
    private CustomerRepoImp customerRepoImp = new CustomerRepoImp();
    private CustomerProjectRepoImp customerProjectRepoImp = new CustomerProjectRepoImp();

    public List<String> getAllCustomers(){
        return customerRepoImp.getAllCustomers();
    }

    public void addCustomer(Customer customer){
        customerRepoImp.save(customer);
        if(!customer.getProjects().isEmpty()){
            customer.setId(getCurrentId());
            customerProjectRepoImp.save(customer);
        }
    }

    private Long getCurrentId(){
        List<String> customers = getAllCustomers();
        String[] cusArr = customers.get(customers.size() - 1).split(",");
        return new Long(cusArr[0]);
    }
}
