package com.popovich.controller;

import com.popovich.model.Customer;
import com.popovich.service.CustomerService;

import java.util.List;

public class CustomerController {
    private CustomerService customerService =new CustomerService();

    public List<String> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    public void addCustomer(Customer customer){
        customerService.addCustomer(customer);
    }
}
