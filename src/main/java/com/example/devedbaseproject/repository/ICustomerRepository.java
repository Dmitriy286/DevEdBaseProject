package com.example.devedbaseproject.repository;

import com.example.devedbaseproject.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {
}
