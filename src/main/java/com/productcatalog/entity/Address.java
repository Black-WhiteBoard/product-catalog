package com.productcatalog.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "address")
public class Address implements Serializable {
    @Id
    private Integer id;
    @Column
    private String city;
    @Column
    private String state;
    @Column
    private String zipCode;

    @Column
    private String type;
    @Column
    private String address1;
    @Column
    private String address2;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
