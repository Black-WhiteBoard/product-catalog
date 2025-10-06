package com.productcatalog.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.productcatalog.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> getProductByName(String name);
    public Product AddProduct(Product product) throws JsonProcessingException;

    List<Product> getAllProducts();
}
