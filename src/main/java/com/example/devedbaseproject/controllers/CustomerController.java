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

    @PostMapping("/customers/filter")
    public String search(@RequestParam("filter") String filter, Model model) {
        Iterable<Customer> customers;
        if (filter != null && !filter.isEmpty()) {
            customers = customerRepository.findByName(filter);
        }
        else {
            customers = customerRepository.findAll();
        }
        model.addAttribute("customers", customers);
        return "customers-filter";
    }

//    @GetMapping("/customers")
//    public String findAll(Model model ){
//        List<Customer> customers = customerRepository.findAll();
//        model.addAttribute("customers", customers);
//        return "customers-list";
//    }

    @GetMapping("employees/customers/{id}")
    public String findAll(@PathVariable Long id, Model model ){
        Customer customers = customerRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid customer ID" + id));
        model.addAttribute("customer", customers);
        return "FRONT/card-customer";
    }
    @GetMapping("/customer-create")
    public String createCustomerForm(Customer customer){

        return "FRONT/customer-create";
    }


    @PostMapping("/customer-create")
    public String createCustomer(Customer customer){
        customerRepository.save(customer);
        return "redirect:/employees/account";
    }

    @GetMapping("/employees/customers/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id){
        customerRepository.deleteById(id);
        return "redirect:/employees/account";
    }

    @GetMapping("/employees/customers/update/{id}")
    public String updateCustomerForm(@PathVariable("id") Long id, Model model){
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid customer ID" + id));
        model.addAttribute("customer", customer);
        return "FRONT/customer-update";
    }

    @PostMapping("/employees/customers/update/{id}")
    public String updateCustomer(Customer customer, @PathVariable("id") Long id){

        customerRepository.save(customer);

        return "redirect:/employees/customers/"+ id;
    }

}
