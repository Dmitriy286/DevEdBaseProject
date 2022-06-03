package com.example.devedbaseproject.repository;

import com.example.devedbaseproject.models.Employee;
import com.example.devedbaseproject.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByName(String name);
}
