//package com.example.devedbaseproject.controllers;
//
//
//import com.example.devedbaseproject.models.OrderDetails;
//import com.example.devedbaseproject.models.Product;
//import com.example.devedbaseproject.models.Role;
//import com.example.devedbaseproject.repository.IOrderDetailsRepository;
//import com.example.devedbaseproject.repository.IProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/orderdetails")
//public class OrderDetailsController {
//    private final IOrderDetailsRepository repository;
//
//    @Autowired
//    public OrderDetailsController(IOrderDetailsRepository repository){
//        this.repository = repository;
//    }
//
//    @GetMapping()
//    public String findAll(Model model) {
//        List<OrderDetails> odList = repository.findAll();
//        model.addAttribute("orderdetails", odList);
//        return "orderdetails/showAll";
//    }
//
//
//
//}
