package com.productcatalog.model;

import lombok.Data;

import java.util.List;

@Data
public class Customer {
    private String firstName;
    private String lastName;
    private Integer customerId;
    private Integer age;
    private List<Address> address;
}
