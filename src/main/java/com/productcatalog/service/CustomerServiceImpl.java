package com.productcatalog.service;


import com.productcatalog.entity.Address;
import com.productcatalog.entity.Customer;
import com.productcatalog.mapping.EntityMapper;
import com.productcatalog.repository.AddressRepo;
import com.productcatalog.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private EntityMapper mapper;

    @Override
    public Integer createCustomer(com.productcatalog.model.Customer customer) {
        Customer customerEnty = mapper.customerModelToEntity(customer);
        List<Address> addressEntyList=customer.getAddress().stream().map(add ->mapper.addressModelToEntity(add) ).collect(Collectors.toList());
        addressRepo.saveAll(addressEntyList);
        Customer customerPersisted= customerRepo.save(customerEnty);
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
}
