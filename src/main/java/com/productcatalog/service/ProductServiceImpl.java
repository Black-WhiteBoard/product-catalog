package com.productcatalog.service;

import com.productcatalog.entity.Product;
import com.productcatalog.exception.DuplicateRecordException;
import com.productcatalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional("kafkaTransactionManager")
    @Override
    public Product AddProduct(Product product) {
        String msg="Product created";
        kafkaTemplate.send("productsTopic",msg.concat(product.getName()));
        if(productRepository.existsById(product.getId()))
            throw new DuplicateRecordException("duplicate record");
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


}
