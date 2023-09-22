package com.sonata.productdetails.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
@Entity
@Component
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private long productId;
    @Column(name="product_name")
    private String productName;
    @Column(name="description")
    private String description;
    @Column(name="price")
    private double price;
    @Column(name="color")
    private String color;
    @Column(name="review")
    private String review;
    @Column(name="stock_available")
    private Integer stockAvailable;
    @Column(name="categories")
    private String categories;
    @Column(name="lastModified")
    private Date lastModified;
    @Column(name="ratings")
    private Integer ratings;

}
