package com.productcatalog.api;

import com.productcatalog.repository.CustomerRepo;
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
    public ResponseEntity<List<Customer>> getCustomer(@RequestParam(name = "name" ,required = false) String name, @RequestParam(name = "city",required = false) String city, @RequestParam(name = "state",required = false) String state) {
        List<Customer> result = customerService.getAllCustomer();
        return  ResponseEntity.ok(result);
    }

    @PostMapping("/customer")
    public ResponseEntity<Integer> createCustomer(@RequestBody Customer customer){
        return ResponseEntity.ok( customerService.createCustomer(customer));
    }
}