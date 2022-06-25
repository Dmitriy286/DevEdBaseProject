package com.example.devedbaseproject.controllers;

import com.example.devedbaseproject.models.Customer;
import com.example.devedbaseproject.models.Employee;
import com.example.devedbaseproject.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CustomerController {

    private final ICustomerRepository customerRepository;

    @Autowired
    public CustomerController(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

//    @GetMapping("/customers/")
//    public String findAll(Model model ){
//        List<Customer> customers = customerRepository.findAll();
//        model.addAttribute("customers", customers);
//        return "customer-list";
//    }

    @PostMapping("/customers")
    public String search(@RequestParam("search") String search, Model model) {
        Iterable<Customer> customers;
        if (search != null && !search.isEmpty()) {
            customers = customerRepository.findByName(search);
        }
        else {
            customers = customerRepository.findAll();
        }
        model.addAttribute("customers", customers);
        return "customers-list";
    }
    @GetMapping("/customers")
    public String findAll(Model model ){
        List<Customer> customers = customerRepository.findAll();
        model.addAttribute("customers", customers);
        return "customers-list";
    }

    @GetMapping("/customers/{id}")
    public String findAll(@PathVariable Long id, Model model ){
        Customer customers = customerRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid customer ID" + id));
        model.addAttribute("customer", customers);
        return "FRONT/card-customer";
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

    @GetMapping("/customers/update/{id}")
    public String updateCustomerForm(@PathVariable("id") Long id, Model model){
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid customer ID" + id));
        model.addAttribute("customer", customer);
        return "customer-update";
    }

//    @PostMapping("/customers/update/")
//    public String updateCustomer(Customer customer){
//
//        customerRepository.save(customer);
//
//        return "redirect:/customers/";
//    }

}
