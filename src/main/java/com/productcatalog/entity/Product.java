package com.productcatalog.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "product")
public class Product implements Serializable {
 public Product(){}
   @JsonProperty
    @Id
    private Integer id;
   @JsonProperty
    @Column(name = "name")
    private  String name;
   @JsonProperty
    @Column(name = "category")
    private String catagory;
}
