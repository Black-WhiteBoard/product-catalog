package com.productcatalog.entity;

import lombok.Data;

import javax.naming.directory.SearchResult;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customer")
@Data
public class Customer implements Serializable {

    @Id
    private Integer customerId;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private Integer age;
    @OneToMany(mappedBy = "customer")
   private Set<Address> address;
}
