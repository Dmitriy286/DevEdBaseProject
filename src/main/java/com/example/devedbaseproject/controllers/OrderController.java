package com.example.devedbaseproject.controllers;

import com.example.devedbaseproject.models.Customer;
import com.example.devedbaseproject.models.Order;
import com.example.devedbaseproject.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/orders")
    public String findAll(Model model){
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "order/orders-list";
    }

    @GetMapping("/order-create")
    public String createOrderForm(Order order){
        return "order/order-create";
    }


    @PostMapping("/order-create")
    public String createOrder(Order order){
        orderRepository.save(order);
        return "redirect:/orders";
    }


    @GetMapping("/order-update/{id}")
    public String updateOrderForm(@PathVariable("id") Long id, Model model){
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid customer ID" + id));
        model.addAttribute("order", order);
        return "order/order-update";
    }

    @PostMapping("/order-update")
    public String updateOrder(Order order){
        orderRepository.save(order);
        return "redirect:/orders";
    }
}
