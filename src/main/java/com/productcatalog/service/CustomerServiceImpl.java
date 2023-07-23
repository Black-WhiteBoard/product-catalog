package com.productcatalog.service;


import com.productcatalog.dao.CustomerDao;
import com.productcatalog.entity.Address;
import com.productcatalog.entity.Customer;
import com.productcatalog.mapping.EntityMapper;
import com.productcatalog.repository.AddressRepo;
import com.productcatalog.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private EntityMapper mapper;
    @Autowired
    private CustomerDao customerDao;

    @Override
    public Integer createCustomer(com.productcatalog.model.Customer customer) {
        Customer customerEnty = mapper.customerModelToEntity(customer);
        Set<Address> addressEntyList=customer.getAddress().stream().map(add ->mapper.addressModelToEntity(add) ).collect(Collectors.toSet());


        Customer customerPersisted= customerRepo.save(customerEnty);
        addressEntyList = addressEntyList.stream().map(address -> {address.setCustomer(customerPersisted); return  address;}).collect(Collectors.toSet());
        addressRepo.saveAll(addressEntyList);
       /* customerPersisted.
        customerRepo.save(customerPersisted);*/
        return customerPersisted.getCustomerId();
    }

    @Override
    public List<com.productcatalog.model.Customer> getCustomer(String name) {
        return null;
    }

    @Override
    public List<com.productcatalog.model.Customer> getAllCustomer() {
        return mapper.customerEntityToModel(customerRepo.findAll());
    }

    @Override
    public List<com.productcatalog.model.Customer> getAllCustomerByCity(String city) {
        return mapper.customerEntityToModel(customerRepo.findByAddressCity(city));
    }

    @Override
    public List<com.productcatalog.model.Customer> getCustomerByNameOrCityOrState(String lastname, String city, String state) {

        return mapper.customerEntityToModel(customerRepo.findByLastnameAndAddress_CityAndAddress_State(lastname,city,state));
    }

    @Override
    public List<com.productcatalog.model.Customer> getCustomerByNameAndCityAndState(String lastname, String city, String state) {
        return mapper.customerEntityToModel(customerDao.fetchCustomerByLastnameAndCityAndState(lastname, city, state));
    }
}
