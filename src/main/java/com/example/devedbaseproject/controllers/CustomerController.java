package com.example.devedbaseproject.controllers;

import com.example.devedbaseproject.models.Customer;
import com.example.devedbaseproject.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customers")
    public String findAll(Model model ){
        List<Customer> customers = customerRepository.findAll();
        model.addAttribute("customers", customers);
        return "customers-list";
    }
    @GetMapping("/customer-create")
    public String createCustomerForm(Customer customer){
        return "customer-create";
    }


    @PostMapping("/customer-create")
    public String createCustomer(Customer customer){
        customerRepository.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/customer-delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id){
        customerRepository.deleteById(id);
        return "redirect:/customers";
    }

    @GetMapping("/customer-update/{id}")
    public String updateCustomerForm(@PathVariable("id") Long id, Model model){
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid customer ID" + id));
        model.addAttribute("customer", customer);
        return "customer-update";
    }

    @PostMapping("/customer-update")
    public String updateCustomer(Customer customer){
        customerRepository.save(customer);
        return "redirect:/customers";
    }
}
