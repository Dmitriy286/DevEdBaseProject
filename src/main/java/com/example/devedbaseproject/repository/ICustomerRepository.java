package com.example.devedbaseproject.repository;

import com.example.devedbaseproject.models.Customer;
import com.example.devedbaseproject.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByName(String name);


}
