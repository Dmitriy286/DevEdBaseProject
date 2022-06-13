package com.example.devedbaseproject.repository;

import com.example.devedbaseproject.models.ProductParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductParameterRepository extends JpaRepository<ProductParameter, Long> {
    Optional<ProductParameter> findByName(String name);

}
