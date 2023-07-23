package com.productcatalog.model;

import lombok.Data;

import java.util.List;

@Data
public class Customer {
    private String firstname;
    private String lastname;
    private Integer customerId;
    private Integer age;
    private List<Address> address;
}
