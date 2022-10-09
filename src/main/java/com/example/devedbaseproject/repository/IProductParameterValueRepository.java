package com.example.devedbaseproject.repository;

import com.example.devedbaseproject.models.ProductParameterValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IProductParameterValueRepository extends JpaRepository<ProductParameterValue, Long> {

}
