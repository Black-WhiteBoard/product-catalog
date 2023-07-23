package com.productcatalog.dao;

import com.productcatalog.entity.Customer;

import java.util.List;

public interface CustomerDao {
    public List<Customer> fetchCustomerByLastnameAndCityAndState(String lastname,String city,String state);
}
