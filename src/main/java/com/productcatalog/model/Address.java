package com.productcatalog.model;

import com.productcatalog.entity.Customer;
import lombok.Data;

import javax.persistence.*;


@Data
public class Address {

    private Integer id;

    private String city;

    private String state;

    private String zipCode;


    private String type;

    private String address1;

    private String address2;
}
