package com.productcatalog.api;

import com.productcatalog.model.Customer;
import com.productcatalog.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerManagmentController {


    @Autowired
    public CustomerService customerService;

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> getCustomer(@RequestParam(name = "lastname" ,required = false) String lastname, @RequestParam(name = "city",required = false) String city, @RequestParam(name = "state",required = false) String state) {
        List<Customer> result=null;
        if(lastname ==null && city==null && state==null )
             result = customerService.getAllCustomer();
        else
            result = customerService.getCustomerByNameAndCityAndState(lastname,city,state);

        return  ResponseEntity.ok(result);
    }

    @GetMapping("/customer/city/{city}")
    public ResponseEntity<List<Customer>> getCustomerByCity(@PathVariable(name = "city",required = false) String city) {
        List<Customer> result = customerService.getAllCustomerByCity(city);
        return  ResponseEntity.ok(result);
    }

    @PostMapping("/customer")
    public ResponseEntity<Integer> createCustomer(@RequestBody Customer customer){
        return ResponseEntity.ok( customerService.createCustomer(customer));
    }
}