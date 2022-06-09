package com.example.devedbaseproject.repository;

import com.example.devedbaseproject.models.ProductParameter;
import com.example.devedbaseproject.models.ProductParameterValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductParameterValueRepository extends JpaRepository<ProductParameterValue, Long> {

}
