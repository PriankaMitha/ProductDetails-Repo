package com.sonata.testproductschema;

import com.sonata.productdetails.ProductdetailsApplication;
import com.sonata.productdetails.entity.Product;
import com.sonata.productdetails.exception.ResourceNotFoundException;
import com.sonata.productdetails.repository.ProductRepository;
import com.sonata.productdetails.serviceimpl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = {com.sonata.productdetails.ProductdetailsApplication.class})
public class TestProductSchema {
    @Autowired
    Product product;
    @Autowired
    ProductServiceImpl productServiceImpl;
    @MockBean
    ProductRepository productRepository;

    Date date = new Date(2023-06-26);

    @BeforeEach
    void setUp(){
        product.setProductId(1001);
        product.setProductName("Mobile");
        product.setDescription("RealMe-3, 128GB storage");
        product.setPrice(19000.08);
        product.setColor("black");
        product.setReview("good");
        product.setLastModified(date);
        product.setCategories("electronics");
        product.setRatings(5);
        product.setStockAvailable(23);
    }
    @Test
    void testGetProductId() {
        assertEquals(1001, product.getProductId());
    }
    @Test
    void testGetProductname() {
        assertEquals("Mobile", product.getProductName());
    }
    @Test
    void testGetDescription() {
        assertEquals("RealMe-3, 128GB storage", product.getDescription());
    }
    @Test
    void testGetPrice() {
        assertEquals(19000.08, product.getPrice());
    }
    @Test
    void testGetReview() {
        assertEquals("good", product.getReview());
    }
    @Test
    void testLastModified() {
        assertEquals(date, product.getLastModified());
    }
    @Test
    void testGetCategories() {
        assertEquals("electronics", product.getCategories());
    }
    @Test
    void testGetRatings() {
        assertEquals(5, product.getRatings());
    }
    @Test
    void testStockAvailable() {
        assertEquals(23, product.getStockAvailable());
    }
    @Test
    void testGetColor() {
        assertEquals("black", product.getColor());
    }

    @Test
    void testGetAllProducts() {
        Mockito.when(productRepository.findAll())
                .thenReturn(Stream.of(product).collect(Collectors.toList()));
        assertEquals(1, productServiceImpl.getAllProducts().size());
    }
    @Test
    void testAddProduct() {
        Mockito.when(productRepository.save(product))
                .thenReturn(product);
        assertEquals(product, productServiceImpl.addProduct(product));
    }

    @Test
    void testGetProductById() throws ResourceNotFoundException {
        Optional<Product> optionalProduct = Optional.of(product);
        Mockito.when(productRepository.findById(product.getProductId())).thenReturn(optionalProduct);
        assertEquals(product, productServiceImpl.getProductById(product.getProductId()));
    }

    @Test
    void testUpdateProduct() {
        Product updatedProduct = new Product(1001,"Mobile","RealMe-3, 128GB storage",20000.09,"blue","good",17,"electronics",date,5);
        Optional<Product> optionalProduct = Optional.of(updatedProduct);
        Mockito.when(productRepository.findById(updatedProduct.getProductId()))
                .thenReturn(optionalProduct);
        Mockito.when(productRepository.save(updatedProduct))
                .thenReturn(updatedProduct);
        assertEquals(updatedProduct, productServiceImpl.updateProduct(updatedProduct));
    }

    @Test
    void testDeleteProduct() throws ResourceNotFoundException {
        Optional<Product> optionalProduct = Optional.of(product);
        Mockito.when(productRepository.findById(product.getProductId()))
                .thenReturn(optionalProduct);
        productServiceImpl.deleteProductById(product.getProductId());
        verify(productRepository, times(1)).deleteById(product.getProductId());
    }


}
