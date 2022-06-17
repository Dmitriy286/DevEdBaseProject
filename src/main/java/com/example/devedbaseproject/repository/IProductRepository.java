package com.example.devedbaseproject.repository;

import com.example.devedbaseproject.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
