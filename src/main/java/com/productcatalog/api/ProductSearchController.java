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

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@RestController
@RequestMapping("/api")
public class ProductSearchController {

    @Autowired
    private ProductService productService;
    @GetMapping(value = "/product/brand/{brand}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable("brand") String brand) {

        List<Product> product=productService.getProductByName(brand);
     if(!product.isEmpty()){
             return ResponseEntity.ok(product);
         }else {
              throw  new DataNotFoundException(String.valueOf(brand));
         }

    }


    @GetMapping(value = "/product/brand" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getAllProducts() {

        List<Product> product=productService.getAllProducts();
        if(!product.isEmpty()){
            return ResponseEntity.ok(product);
        }else {
            throw  new DataNotFoundException("No data");
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
