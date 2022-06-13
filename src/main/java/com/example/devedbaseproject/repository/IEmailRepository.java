package com.example.devedbaseproject.repository;

import com.example.devedbaseproject.models.Customer;
import com.example.devedbaseproject.models.Email;
import com.example.devedbaseproject.models.Employee;
import com.example.devedbaseproject.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEmailRepository extends JpaRepository<Email, Long> {
    List<Email> findByDate(String date);
    Optional<Email> findByCustomer(Customer customer);

}
