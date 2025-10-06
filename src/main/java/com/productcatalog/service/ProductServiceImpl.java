package com.productcatalog.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.productcatalog.entity.Product;
import com.productcatalog.exception.DuplicateRecordException;
import com.productcatalog.repository.ProductRepository;
import org.apache.kafka.common.protocol.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import org.springframework.kafka.support.serializer.ParseStringDeserializer;
import org.springframework.kafka.support.serializer.StringOrBytesSerializer;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.kafka.support.serializer.StringOrBytesSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;

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
    public Product AddProduct(Product product) throws JsonProcessingException {
        String msg="Product created";

        ObjectMapper mapper =new ObjectMapper();
        String ojbectAsString =null;
         try {
              ojbectAsString= mapper.writeValueAsString(product);
         }catch (Exception e){
            throw e;
         }

        kafkaTemplate.send("productsTopic","products-group-1",ojbectAsString);

        if(productRepository.existsById(product.getId()))
            throw new DuplicateRecordException("duplicate record");
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


}
