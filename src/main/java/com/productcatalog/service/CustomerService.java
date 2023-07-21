package com.productcatalog.service;

import com.productcatalog.model.Customer;

import java.util.List;

public interface CustomerService {
    public Integer createCustomer(Customer customer);
    public List<Customer> getCustomer(String name);

    List<Customer> getAllCustomer();
}
