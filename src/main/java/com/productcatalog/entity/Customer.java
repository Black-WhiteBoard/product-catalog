package com.productcatalog.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "customer")
@Data
public class Customer implements Serializable {

    @Id
    private Integer customerId;
    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private Integer age;
    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
   private List<Address> address;
}
