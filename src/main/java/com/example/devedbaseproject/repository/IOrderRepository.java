package com.example.devedbaseproject.repository;

import com.example.devedbaseproject.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Long> {
}
