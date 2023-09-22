package com.sonata.productdetails.serviceimpl;

import com.sonata.productdetails.entity.Product;
import com.sonata.productdetails.exception.ResourceNotFoundException;
import com.sonata.productdetails.repository.ProductRepository;
import com.sonata.productdetails.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(long productId) throws ResourceNotFoundException{
        return productRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("Product not exist with Id : "+productId));
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) throws ResourceNotFoundException{
        Product existingProduct = productRepository.findById(product.getProductId())
                .orElseThrow(()->new ResourceNotFoundException("Product not exist with Id : "+product.getProductId()));
        if(product.getProductName()!=null) {
            existingProduct.setProductName(product.getProductName());
        }
        if(product.getDescription()!=null) {
            existingProduct.setDescription(product.getDescription());
        }
        if(product.getPrice()!= 0) {
            existingProduct.setPrice(product.getPrice());;
        }
        if(product.getCategories()!=null){
            existingProduct.setCategories(product.getCategories());
        }
        if(product.getColor()!=null){
            existingProduct.setColor(product.getColor());
        }
        if(product.getLastModified()!=null){
            existingProduct.setLastModified(product.getLastModified());
        }
        if(product.getRatings()!=null) {
            existingProduct.setRatings(product.getRatings());
        }
        if(product.getReview()!=null){
            existingProduct.setReview(product.getReview());
        }
        if(product.getStockAvailable()!=null){
            existingProduct.setStockAvailable(product.getStockAvailable());
        }
        Product updatedProduct = productRepository.save(existingProduct);
        return updatedProduct;
    }

    @Override
    public Product deleteProductById(long productId) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("Product not exist with Id : "+productId));
        productRepository.deleteById(productId);
        return product;
    }
}
