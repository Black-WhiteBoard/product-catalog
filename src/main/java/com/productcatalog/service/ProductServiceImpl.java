package com.productcatalog.service;

import com.productcatalog.entity.Product;
import com.productcatalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public List<Product> getProductByName(String id) {
        return productRepository.findByNameIgnoreCase(id);
    }

    @Override
    public Product AddProduct(Product product) {
        kafkaTemplate.send("productsTopic","New product: created");
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


}
