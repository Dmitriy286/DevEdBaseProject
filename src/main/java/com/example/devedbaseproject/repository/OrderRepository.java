package com.example.devedbaseproject.repository;

import com.example.devedbaseproject.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
