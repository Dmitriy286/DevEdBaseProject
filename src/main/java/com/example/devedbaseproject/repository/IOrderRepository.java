package com.example.devedbaseproject.repository;

import com.example.devedbaseproject.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderRepository extends JpaRepository<Order, Long> {
//    List<Order> findByCustomer(String customer);
}
