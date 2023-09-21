package com.sonata.productdetails.service;

import com.sonata.productdetails.entity.Product;
import com.sonata.productdetails.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> getAllProducts();
    public Product getProductById(long productId) throws ResourceNotFoundException;
    public Product addProduct(Product product);
    public Product updateProduct(Product product) throws ResourceNotFoundException;
    public Product deleteProductById(long productId) throws ResourceNotFoundException;
}
