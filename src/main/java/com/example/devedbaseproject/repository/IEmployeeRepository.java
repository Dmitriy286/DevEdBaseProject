package com.example.devedbaseproject.repository;

import com.example.devedbaseproject.models.Employee;
import com.example.devedbaseproject.models.Role;
import org.hibernate.boot.model.source.spi.Sortable;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

//    List<Employee> findAll(Sortable sortable);
    List<Employee> findByName(String name);
    Employee findByUsername(String username);
}
