package com.example.devedbaseproject.repository;

import com.example.devedbaseproject.models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductTypeRepository extends JpaRepository<ProductType, Long> {

}
