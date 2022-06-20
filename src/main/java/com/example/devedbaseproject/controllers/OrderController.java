package com.example.devedbaseproject.controllers;

import com.example.devedbaseproject.models.*;
import com.example.devedbaseproject.repository.ICustomerRepository;
import com.example.devedbaseproject.repository.IEmployeeRepository;
import com.example.devedbaseproject.repository.IOrderRepository;
import com.example.devedbaseproject.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class OrderController {

    private final IOrderRepository orderRepository;
    private final ICustomerRepository customerRepository;
    private final IProductRepository productRepository;
    private final IEmployeeRepository employeeRepository;

    @Autowired
    public OrderController(IOrderRepository orderRepository, ICustomerRepository customerRepository, IProductRepository productRepository, IEmployeeRepository employeeRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/orders")
    public String findAll(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "order/orders-list";
    }

    @GetMapping("/order-update/{id}")
    public String updateOrderForm(@PathVariable("id") Long id, Model model) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid customer ID" + id));
        model.addAttribute("order", order);
        Customer customer = order.getCustomer();
        Employee employee = order.getEmployee();
        model.addAttribute("customer", customer);
        model.addAttribute("employee", employee);
        model.addAttribute("products", productRepository.findAll());
        return "order/order-update";
    }

    @PostMapping("/order-update")
    public String updateOrder(@Valid Order order, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "order-update";
        }
        order.setActionDateTime(getLocalDateTime());
        order.setOrderCost(200);
        order.setOrderStatus("Не оплачен");
        orderRepository.save(order);
        return "redirect:/orders";
    }

    @GetMapping("/order-create")
    public String createOrderFrom(Order order, Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("employees", employeeRepository.findAll());
        return "order/order-create";
    }

    @PostMapping("/order-create")
    public String createOrder(@Valid Order order, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "order-create";
        }
        order.setActionDateTime(getLocalDateTime());
        order.setOrderCost(200);
        order.setOrderStatus("Не оплачен");
        orderRepository.save(order);
        return "redirect:/orders";
    }
    @GetMapping("/order-details/{id}")
    public String detailsWiew(@PathVariable("id") Long id, Model model) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid customer ID" + id));
        model.addAttribute("order", order);
        Customer customer = order.getCustomer();
        Employee employee = order.getEmployee();
        OrderDetails orderDetails = order.getOrderDetails();
        List<Product> products = orderDetails.getProductList();
        model.addAttribute("customer", customer);
        model.addAttribute("employee", employee);
        model.addAttribute("prodList", products);
        return "order/order-details";
    }

    private String getLocalDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        LocalDateTime dateTime = LocalDateTime.now();
        return dateTime.format(formatter);
    }
}
