package com.productcatalog.repository;

import com.productcatalog.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {

    public List<Customer> findByFirstName(String name);
   // public List<Customer> findByCity(String city);
  //  public List<Customer> findByState(String state);
    //@NamedQuery("SELECT CUSTOMER c WHERE NAME=?1 and c.address.CITY=?2 AND STATE=?3")
   // public List<Customer> findByNameCityState(String name,String city,String state);
}
