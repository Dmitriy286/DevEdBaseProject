package com.example.devedbaseproject.repository;

import com.example.devedbaseproject.models.Customer;
import com.example.devedbaseproject.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductName(String productName);
}
