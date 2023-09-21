package com.sonata.productdetails.repository;

import com.sonata.productdetails.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProductRepository extends JpaRepository<Product,Long> {
}
