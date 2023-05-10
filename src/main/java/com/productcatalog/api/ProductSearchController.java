package com.productcatalog.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductSearchController {

    @GetMapping("/product/brand/{brand}")
    public ResponseEntity<String> getProductsByBrand(@PathVariable("brand") String brand) {
       return ResponseEntity.ok("product");
    }
}
