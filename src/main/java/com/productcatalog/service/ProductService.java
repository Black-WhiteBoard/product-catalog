package com.productcatalog.service;

import com.productcatalog.entity.Product;

import java.util.Optional;

public interface ProductService {
    public Optional<Product> getProduct(Integer id);
    public Product AddProduct(Product product);
}
