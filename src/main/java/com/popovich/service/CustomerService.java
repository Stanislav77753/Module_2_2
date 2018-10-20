package com.popovich.service;

import com.popovich.model.Customer;
import com.popovich.repository.repoImp.CustomerRepoImp;

import java.util.List;

public class CustomerService {
    private CustomerRepoImp customerRepoImp = new CustomerRepoImp();

    public List<String> getAllCustomers(){
        return customerRepoImp.getAllCustomers();
    }

    public void addCustomer(Customer customer){
        customerRepoImp.save(customer);
    }
}
