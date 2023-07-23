package com.productcatalog.mapping;

import com.productcatalog.entity.Address;
import com.productcatalog.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityMapper {

    public  List<com.productcatalog.model.Customer> customerEntityToModel(List<Customer> all) {
        return all.stream().map(customer ->  customerEntityToModel(customer)).collect(Collectors.toList());
    }

    public Customer customerModelToEntity(com.productcatalog.model.Customer  customerModel){
        Customer  customerEnty =new Customer();
        customerEnty.setCustomerId(customerModel.getCustomerId());
        customerEnty.setAge(customerModel.getAge());
        customerEnty.setFirstname(customerModel.getFirstname());
        customerEnty.setLastname(customerModel.getLastname());
       // List<Address> addresses =customerModel.getAddress().stream().map(add->addressModelToEntity(add)).map(add1->{add1.setCustomer(customerEnty);return add1;}).collect(Collectors.toList());

        /* Set<Address> addresses =customerModel.getAddress().stream().map(add->addressModelToEntity(add)).collect(Collectors.toSet());
          for(Address add :addresses) {
              add.setCustomer(customerEnty);
          }

        customerEnty.setAddress(addresses);*/
        return customerEnty;
    }

    public Address addressModelToEntity(com.productcatalog.model.Address address) {
        Address addressEty =new Address();
        addressEty.setId(address.getId());
        addressEty.setAddress2(address.getAddress2());
        addressEty.setCity(address.getCity());
        addressEty.setState(address.getState());
        return addressEty;
    }

    public com.productcatalog.model.Address addressEntityToModel(Address address) {
        com.productcatalog.model.Address addressModel =new com.productcatalog.model.Address();
        addressModel.setId(address.getId());
        addressModel.setAddress2(address.getAddress2());
        addressModel.setCity(address.getCity());
        addressModel.setState(address.getState());
        return addressModel;
    }

    public com.productcatalog.model.Customer customerEntityToModel(Customer  customerEty){
        com.productcatalog.model.Customer  customerModel =new com.productcatalog.model.Customer();
        customerModel.setCustomerId(customerEty.getCustomerId());
        customerModel.setAge(customerEty.getAge());
        customerModel.setFirstname(customerEty.getFirstname());
        customerModel.setLastname(customerEty.getLastname());
        customerModel.setAddress(customerEty.getAddress().stream().map(add->addressEntityToModel(add)).collect(Collectors.toList()));
        return customerModel;
    }
}
