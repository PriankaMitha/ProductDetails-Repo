package com.sonata.productdetails.controller;

import com.sonata.productdetails.entity.Product;
import com.sonata.productdetails.exception.ResourceNotFoundException;
import com.sonata.productdetails.serviceimpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product/")
public class ProductController {
    @Autowired
    private ProductServiceImpl productServiceImpl;

    @GetMapping("getAll")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> productList = productServiceImpl.getAllProducts();
        return ResponseEntity.ok(productList);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long productId) throws ResourceNotFoundException {
        return ResponseEntity.ok(productServiceImpl.getProductById(productId));
    }
    @PostMapping("addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        return ResponseEntity.ok(productServiceImpl.addProduct(product));
    }

    @PutMapping("updateProduct")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws ResourceNotFoundException{
        return ResponseEntity.ok(productServiceImpl.updateProduct(product));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable long id) throws ResourceNotFoundException{
        productServiceImpl.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
