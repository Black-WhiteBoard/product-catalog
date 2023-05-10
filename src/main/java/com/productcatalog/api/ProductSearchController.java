package com.productcatalog.api;

import com.productcatalog.entity.Product;
import com.productcatalog.exception.ApplicationException;
import com.productcatalog.exception.DataNotFoundException;
import com.productcatalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.OptionalDouble;

@RestController
@RequestMapping("/api")
public class ProductSearchController {

    @Autowired
    private ProductService productService;
    @GetMapping(value = "/product/brand/{brand}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductsByBrand(@PathVariable("brand") Integer brand) {

        Optional<Product> product=productService.getProduct(brand);
         if(product.isPresent()){
             return ResponseEntity.ok(product.get());
         }else {
              throw  new DataNotFoundException(String.valueOf(brand));
         }

    }

    @PostMapping(value = "/product",produces = MediaType.APPLICATION_JSON_VALUE,consumes =MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<String> addProduct( @RequestBody Product product){
        Product addedProduct=productService.AddProduct(product);
         if (addedProduct!=null) {
             return  ResponseEntity.ok("Product added successfully !");
         }else{
             throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR.toString());
         }
    }
}
