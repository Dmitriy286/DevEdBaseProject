//package com.example.devedbaseproject.controllers;
//
//import com.example.devedbaseproject.models.Employee;
//import com.example.devedbaseproject.repository.ICustomerRepository;
//import com.example.devedbaseproject.repository.IEmployeeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class EmployeeAccount {
//
//    private final ICustomerRepository customerRepository;
//    private final IEmployeeRepository employeeRepository;
//
//    public EmployeeAccount(ICustomerRepository customerRepository, IEmployeeRepository employeeRepository) {
//        this.customerRepository = customerRepository;
//        this.employeeRepository = employeeRepository;
//    }
//
//    @Autowired
//    @GetMapping("/employee-account/")
//    public String showEmployeeAccount(@AuthenticationPrincipal Employee employee, Model model){
//        model.addAttribute("employeeAccount", employee);
//
////        List<Customer> customers = customerRepository.findAll();
////        model.addAttribute("customers", customers);
//        return "FRONT/employees-account";
//    }
//
//
//}
