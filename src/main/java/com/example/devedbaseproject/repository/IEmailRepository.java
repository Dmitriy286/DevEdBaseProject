package com.example.devedbaseproject.repository;

import com.example.devedbaseproject.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmailRepository extends JpaRepository<Email, Long> {
    List<Email> findByDate(String date);
    List<Email> findByProduct(Product product);
}
